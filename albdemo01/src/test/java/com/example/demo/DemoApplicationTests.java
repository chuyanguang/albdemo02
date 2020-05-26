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
            fc.read(buffer);
            buffer.flip();
            byte[] bytes = new byte[1024];
            while (buffer.hasRemaining()) {
                buffer.get(bytes, 0, buffer.limit());
                System.out.println(new String(bytes, Charset.forName("UTF-8")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
