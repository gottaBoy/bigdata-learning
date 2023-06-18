package com.my.netty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.my.util.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.IOException;

//@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        if (msg instanceof ByteBuf) {
//            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
//        }
//        ctx.channel().writeAndFlush("收到信息了");
        RequestFuture requestFuture = null;
        try {
            requestFuture = JsonUtil.parse(msg.toString(), RequestFuture.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        response.setResult("response server success: " + requestFuture.getId());
        response.setId(requestFuture.getId());
        String responseStr = null;
        try {
            responseStr = JsonUtil.generate(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ctx.channel().writeAndFlush(responseStr);
    }
}
