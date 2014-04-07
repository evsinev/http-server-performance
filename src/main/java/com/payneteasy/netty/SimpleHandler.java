package com.payneteasy.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;

public class SimpleHandler extends SimpleChannelInboundHandler<HttpObject> {
    FullHttpResponse response;
    protected SimpleHandler() {
        response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer("test\n", CharsetUtil.UTF_8));
        response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
//        System.out.println("msg = " + msg);
//        if(msg instanceof LastHttpContent) {
            ctx.channel().write(response).addListener(ChannelFutureListener.CLOSE);
//        }
    }
}
