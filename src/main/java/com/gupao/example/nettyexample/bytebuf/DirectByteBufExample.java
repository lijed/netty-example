/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.gupao.example.nettyexample.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.buffer.UnpooledUnsafeHeapByteBuf;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/9/10
 **/
public class DirectByteBufExample {
    public static void main(String[] args) {
        UnpooledDirectByteBuf unpooledDirectByteBuf= null;
        UnpooledUnsafeHeapByteBuf unpooledUnsafeHeapByteBuf = null;

        ByteBuf directByteBuf = UnpooledByteBufAllocator.DEFAULT.buffer();
    }
}
