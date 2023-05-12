package org.education.aa.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

/**
 * Socket 初始化器，每一个Channel进来都会调用这里的 InitChannel 方法
 *
 * @author fbin
 * @since 2023-05-11
 **/
@Component
public class SocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 添加对byte数组的编解码，netty提供了很多编解码器，你们可以根据需要选择
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        // 添加WebSocket
        pipeline.addLast(new WebSocketServerProtocolHandler("/", "WebSocket", true, 65536 * 10));
        // 添加上自己的处理器
        pipeline.addLast(new CustomWebSocketHandler());
    }
}
