package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class Demo01Test {

    private static final Logger logger = LoggerFactory.getLogger(Demo01Test.class);

    /**
     * 服务器端接收文件、读取客户端上传的文件
     *
     * @throws IOException
     */
    @Test
    public void server() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8989));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel channel = serverChannel.accept();
                    // 开启非阻塞模式
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_WRITE);
                    logger.info("{} link server", channel.getRemoteAddress());
                } else if (selectionKey.isReadable()) {      // 客户端上传信息读取
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (channel.read(buffer) != -1) {
                        buffer.flip();
                        logger.info(new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();
                    }
                    logger.info("data read success");
                } else if (selectionKey.isWritable()) {   // 上传的文件写入服务器
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    FileChannel fileChannel = FileChannel.open(Paths.get("/Users/ygc/Desktop/2.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    while (channel.read(buffer) != -1) {
                        buffer.flip();
                        fileChannel.write(buffer);
                        buffer.clear();
                    }
                    logger.info("server file write success");
                    // 发送反馈给客户端
                    buffer.put("server file write success".getBytes());
                    buffer.flip();
                    channel.write(buffer);

                    fileChannel.close();
                    channel.close();
                }
            }
            // 移除key，防止重复处理操作
            keyIterator.remove();
        }
        serverChannel.close();
    }

    /**
     * 客户端文件上传
     *
     * @throws IOException
     */
    @Test
    public void client() throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8989));) {
            socketChannel.configureBlocking(false);

//        ByteBuffer buffer = ByteBuffer.allocate(1024);

            FileChannel fileChannel = FileChannel.open(Paths.get("/Users/ygc/Desktop/1.txt"), StandardOpenOption.READ);
//        while (fileChannel.read(buffer) != -1){
//            buffer.flip();
//            socketChannel.write(buffer);
//            buffer.clear();
//        }

            fileChannel.transferTo(0, fileChannel.size(), socketChannel);

//        buffer.put(LocalDateTime.now().toString().getBytes());
//        buffer.flip();
//        socketChannel.write(buffer);
//        buffer.clear();

            // 单向关闭输出流，作用：通知服务器文件上传完毕，服务器端才会发送反馈信息
            socketChannel.shutdownOutput();
            // 接收服务器的反馈
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            if (socketChannel.read(buffer) != -1) {
                buffer.flip();
                logger.info(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        }


    }

}
