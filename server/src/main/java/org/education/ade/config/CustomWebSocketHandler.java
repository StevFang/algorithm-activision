package org.education.ade.config;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义WebSocket
 *
 * @author fbin
 * @since 2023-05-11
 */
@Slf4j
public class CustomWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static ChannelGroup channelGroup;
    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到的数据：" + msg.text());
        sendAllMessage();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端建立连接，通道开启");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("与客户端断开连接，通道关闭");
        channelGroup.remove(ctx.channel());
    }

    private void sendMessage(ChannelHandlerContext ctx) {
        String message = "你好，" + ctx.channel().localAddress() + " 给固定的人发消息";
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }

    private void sendAllMessage() {
        String message = "我是服务器，这里发送的是群消息";
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }
}
