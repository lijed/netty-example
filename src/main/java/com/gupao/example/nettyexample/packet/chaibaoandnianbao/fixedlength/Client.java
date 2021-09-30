/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.gupao.example.nettyexample.packet.chaibaoandnianbao.fixedlength;

import com.gupao.example.nettyexample.packet.chaibaoandnianbao.case1.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/9/7
 **/
public class Client {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8181;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new FirstClientHandler()).addLast(new FixedLengthFrameDecoder(36));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)

            Channel channel = f.channel();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
