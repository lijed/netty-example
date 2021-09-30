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
public class ByteBufCopyExmaple {
    public static void main(String[] args) {
        //  零拷贝

        ByteBuf request = ByteBufAllocator.DEFAULT.buffer();
        request.writeBytes(new byte[] {1, 2 , 3, 4, 5, 6, 7, 8, 9 , 10 });
        log(request);
        //从buf这个总的数据中，分别拆分5个字节保存到两个ByteBuf中
        //零拷贝机制    (浅克隆)
        final ByteBuf front = request.slice(0, 5);
        final ByteBuf end = request.slice(5, 10);

        log(front);
        log(end);

        System.out.println("修改原始数据");
        front.setByte(2, 9);

        log(front);
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
