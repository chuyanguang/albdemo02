package com.example.demo;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    public void test01() {
        try (FileChannel fc = FileChannel.open(Paths.get("D:/test.txt"), StandardOpenOption.READ);) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] bytes = new byte[1024];
            while (fc.read(buffer) != -1){
                buffer.flip();
                buffer.get(bytes, 0, buffer.limit());
                System.out.println(new String(bytes, Charset.forName("UTF-8")));
                buffer.clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

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

}
