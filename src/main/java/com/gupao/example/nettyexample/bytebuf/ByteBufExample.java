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
 * Created: 2021/9/8
 **/
public class ByteBufExample {
    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        for (int i = 0; i <= 400; i++) {
            byteBuf.writeBytes(("-" + i).getBytes());
        }
        
        log(byteBuf);
    }

    private static void log(ByteBuf byteBuf) {
        System.out.println("readIndex: " + byteBuf.readerIndex());
        System.out.println("writeIndex: " + byteBuf.writerIndex());
        StringBuilder stringBuilder = new StringBuilder();
        ByteBufUtil.appendPrettyHexDump(stringBuilder, byteBuf);
        System.out.println(stringBuilder.toString());
    }
}
