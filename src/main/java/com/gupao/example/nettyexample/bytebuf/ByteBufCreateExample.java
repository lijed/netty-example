/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.gupao.example.nettyexample.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/9/9
 **/
public class ByteBufCreateExample {
    public static void main(String[] args) {
        //direct buffer
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();

        //JVM heap buffer
        //ByteBuf headBuf = ByteBufAllocator.DEFAULT.heapBuffer();

        buf.writeBytes(new byte[] {1, 2, 3, 4});

        log(buf);

        //一个int占4个字节
        buf.writeInt(5);

        log(buf);

        System.out.println("开始进行读取");

        buf.markReaderIndex();
        byte b = buf.readByte();

        System.out.println("读到一个byte："+ b);
        log(buf);

        //重新回到标记位置
        buf.resetReaderIndex();
        log(buf);

    }

    private static void log(ByteBuf buf) {
        StringBuilder sb=new StringBuilder();
        sb.append(" read index:").append(buf.readerIndex());  //读索引
        sb.append(" write index:").append(buf.writerIndex()); //写索引
        sb.append(" capacity :").append(buf.capacity()) ; //容量
        ByteBufUtil.appendPrettyHexDump(sb,buf);
        System.out.println(sb.toString());
    }
}
