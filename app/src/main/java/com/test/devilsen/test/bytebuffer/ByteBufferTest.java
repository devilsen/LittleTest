package com.test.devilsen.test.bytebuffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ByteBufferTest {

    public static final String CONTENT_CHARSET = "UTF-8";

    public static void allocate() {

        System.out.println("----------Test allocate--------");
        System.out.println("before alocate:"
                + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);

        System.out.println("after alocate:"
                + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:"
                + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }

    public static void wrap(String channel) {
        try {
            System.out.println("----------- test wrap -------------");
            System.out.println("channel = " + channel);
            byte[] bytes = channel.getBytes(CONTENT_CHARSET);
            System.out.println("bytes = " + Arrays.toString(bytes));
            ByteBuffer channelByteBuffer = ByteBuffer.wrap(bytes);
            System.out.println("channelByteBuffer = " + channelByteBuffer);
//            System.out.println("getLong = " + channelByteBuffer.getLong());
            System.out.println("getInt = " + channelByteBuffer.getShort());
            channelByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            System.out.println("channelByteBuffer = " + channelByteBuffer);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
