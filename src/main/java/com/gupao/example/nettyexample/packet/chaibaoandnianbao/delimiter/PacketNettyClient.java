package com.gupao.example.nettyexample.packet.chaibaoandnianbao.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

import java.io.UnsupportedEncodingException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class PacketNettyClient {

    public static void main(String[] args) throws UnsupportedEncodingException {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                        ch.pipeline()
                                //表示传输消息的时候，在消息报文中增加4个字节的length。->发送的ByteBuf
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {

                                        ctx.writeAndFlush("i am first request&the second reqeuest is send&");
                                        super.channelActive(ctx);
                                    }



                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf byteBuf = (ByteBuf) msg;
                                        byte[] data = new byte[byteBuf.readableBytes()];
                                        byteBuf.readBytes(data);
                                        String str = new String(data);
                                        System.out.println("收到服务端的消息：" + str + ", 消息长度 " + str.length());
                                        super.channelRead(ctx, msg);
                                    }
                                });
                    }
                });
        try {
            ChannelFuture future=bootstrap.connect("localhost",8181).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
