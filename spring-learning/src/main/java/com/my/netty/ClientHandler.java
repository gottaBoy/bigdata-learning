package com.my.netty;

import com.my.util.JsonUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof ByteBuf) {
//            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
//        }
//        ctx.channel().writeAndFlush("收到信息了");
        Response response = JsonUtil.parse(msg.toString(), Response.class);
        RequestFuture.completeSuccess(response);
    }
}
