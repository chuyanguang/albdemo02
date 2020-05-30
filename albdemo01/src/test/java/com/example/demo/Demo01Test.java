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

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8989));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0){
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()){
                    SocketChannel channel = serverChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_WRITE);
                } else if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (channel.read(buffer) != -1){
                        buffer.flip();
                        logger.info(new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();
                    }
                } else if(selectionKey.isWritable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    FileChannel fileChannel = FileChannel.open(Paths.get("D://demo.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    while (channel.read(buffer) != -1){
                        buffer.flip();
                        fileChannel.write(buffer);
                        buffer.clear();
                    }
                    logger.info("success");
                    fileChannel.close();
                    channel.close();
                }
            }
            keyIterator.remove();
        }
        serverChannel.close();
    }

    @Test
    public void client() throws IOException {
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8989));
        clientChannel.configureBlocking(false);

//        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileChannel fileChannel = FileChannel.open(Paths.get("D://test.txt"), StandardOpenOption.READ);
//        while (fileChannel.read(buffer) != -1){
//            buffer.flip();
//            clientChannel.write(buffer);
//            buffer.clear();
//        }

        fileChannel.transferTo(0, fileChannel.size(), clientChannel);

//        buffer.put(LocalDateTime.now().toString().getBytes());
//        buffer.flip();
//        clientChannel.write(buffer);
//        buffer.clear();

        clientChannel.close();
    }


}
