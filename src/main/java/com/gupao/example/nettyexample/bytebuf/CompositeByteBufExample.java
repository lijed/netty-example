/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.gupao.example.nettyexample.bytebuf;

import io.netty.buffer.*;

import java.nio.ByteBuffer;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/9/9
 **/
public class CompositeByteBufExample {
    public static void main(String[] args) {
        ByteBuf header = ByteBufAllocator.DEFAULT.buffer();
        header.writeBytes(new byte[] {1,2,3,4,5});
        log(header);
        ByteBuf body = ByteBufAllocator.DEFAULT.buffer();
        body.writeBytes(new byte[] {6,6,8,9,10});
        log(body);
//        normalCombindBytes(header, body);

        //第二个零拷贝实现
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        //第一个boolean: true: 自动增加writeIndex
        compositeByteBuf.addComponents(true, header, body);

        //不会增加writebufffer的writeIndex
//        compositeByteBuf.addComponents(header, body);
        log(compositeByteBuf);
        //更改header里的第一个字节，合并后的byteBuf 同样更改，所以说浅克隆，指向同一个地址
        header.setByte(0, 8);
        log(compositeByteBuf);

//        ByteBuf totalByteBuf = Unpooled.wrappedBuffer(header, body);
//        log(totalByteBuf);
    }

    private static void normalCombindBytes(ByteBuf header, ByteBuf body) {
        //Norally
        ByteBuf request = Unpooled.buffer(header.readableBytes() + body.readableBytes());
        request.writeBytes(header);
        request.writeBytes(body);

        log(request);
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
