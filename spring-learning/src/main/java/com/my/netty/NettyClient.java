package com.my.netty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.util.JsonUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class NettyClient {
    public static EventLoopGroup group = null;
    public static Bootstrap bootstrap = null;
    public static ChannelFuture channelFuture = null;
    static {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        final ClientHandler clientHandler = new ClientHandler();
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                socketChannel.pipeline().addLast(new StringDecoder());
                socketChannel.pipeline().addLast(clientHandler);
                socketChannel.pipeline().addLast(new LengthFieldPrepender(4, false));
                socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
            }
        });
        try {
            channelFuture = bootstrap.connect("localhost",8081).sync();
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Object sentRequest(String msg) {
        RequestFuture requestFuture = new RequestFuture();
        requestFuture.setRequest(msg);
        RequestFuture.addFuture(requestFuture);
        try {
            channelFuture.channel().writeAndFlush(JsonUtil.generate(requestFuture));
            Object result = requestFuture.get();
            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        for (int i = 0; i < 100; i++) {
            Object result = nettyClient.sentRequest("hello world ");
            System.out.println(result);
        }
    }
}
