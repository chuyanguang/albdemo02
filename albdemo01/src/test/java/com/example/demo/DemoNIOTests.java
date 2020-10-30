package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

//@SpringBootTest
class DemoNIOTests {

    /**
     * 读取文本文件内容
     */
    @Test
    public void test01() {
        try (FileChannel fc = FileChannel.open(Paths.get("/Users/ygc/Desktop/mydocs/notes"), StandardOpenOption.READ);) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] bytes = new byte[1024];
            while (fc.read(buffer) != -1) {
                buffer.flip();
                buffer.get(bytes, 0, buffer.limit());
                System.out.println(new String(bytes, Charset.forName("UTF-8")));
                buffer.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 复制文件
     */
    @Test
    public void test02() {
        try (
                FileChannel fc = FileChannel.open(Paths.get("D:/test.txt"), StandardOpenOption.READ);
                FileChannel fc2 = FileChannel.open(Paths.get("D:/abc.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)
        ) {
            /*第一种方式*/
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            while(fc.read(buffer) != -1){
//                buffer.flip();
//                fc2.write(buffer);
//                buffer.clear();
//            }
            /*第二种方式*/
//            fc.transferTo(0, fc.size(), fc2);
            // 或者
            fc2.transferFrom(fc, 0, fc.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 分散与聚集
     */
    @Test
    public void test03() throws Exception {
        RandomAccessFile raf1 = new RandomAccessFile("/Users/ygc/Desktop/1.txt", "rw");//1.获取通道
        FileChannel channel1 = raf1.getChannel();
        //2.分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(1024);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        //3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        RandomAccessFile raf2 = new RandomAccessFile("/Users/ygc/Desktop/2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        while (channel1.read(bufs) != -1){
            Arrays.asList(bufs).forEach(Buffer::flip);
            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit(), StandardCharsets.UTF_8));
            System.out.println("---------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit(), StandardCharsets.UTF_8));
            //4.聚集写入
            channel2.write(bufs);
            Arrays.asList(bufs).forEach(Buffer::clear);
        }
        channel1.close();
        channel2.close();
    }
}
