/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.gupao.example.nettyexample.bytebuf;

import io.netty.buffer.*;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/9/10
 **/
public class UnpooledUnsaftHeapBufferExample {
    public static void main(String[] args) {
        ByteBuf byteBuf  =  Unpooled.buffer();
        System.out.println(byteBuf);

        final ByteBuf byteBuf1 = ByteBufAllocator.DEFAULT.heapBuffer();
        System.out.println(byteBuf1);

        UnpooledUnsafeHeapByteBuf unpooledUnsafeHeapByteBuf;
        UnpooledHeapByteBuf unpooledHeapByteBuf;
    }
}
