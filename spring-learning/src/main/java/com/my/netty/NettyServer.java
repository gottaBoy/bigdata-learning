package com.my.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * https://github.com/jwpttcg66/NettyGameServer
 * https://gitee.com/eel2/iogame
 * https://github.com/bupt1987/JgFramework
 * https://www.yuque.com/iohao/game/wwvg7z
 * https://github.com/kingston-csj/jforgame
 * java:https://github.com/donnie4w/atim
 * go:https://github.com/donnie4w/timgo
 * Obj-C:https://github.com/3990995/tim-objc
 * kotlin:https://github.com/donnie4w/timkotlin
 * epoll_create
 * epoll_ctl
 * epoll_wait
 *
 * 中断机制 rdlist
 *
 * select poll epoll  数组，链表，哈希表
 */
public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    socketChannel.pipeline().addLast(new StringDecoder());
                    socketChannel.pipeline().addLast(new ServerHandler());
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(4, false));
                    socketChannel.pipeline().addLast(new StringEncoder());
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
