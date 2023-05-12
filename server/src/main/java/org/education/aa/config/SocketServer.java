package org.education.aa.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Netty Socket Server
 *
 * @author fbin
 * @since 2023-05-11
 **/
@Slf4j
@Component
public class SocketServer {
    @Resource
    private SocketInitializer socketInitializer;

    @Getter
    private ServerBootstrap serverBootstrap;

    /**
     * netty服务监听端口
     */
    @Value("${netty.port:8088}")
    private int port;
    /**
     * 主线程组数量
     */
    @Value("${netty.bossThread:1}")
    private int bossThread;

    /**
     * 启动netty服务器
     */
    public void start() throws InterruptedException {
        // 创建两个线程组，bossGroup为接收请求的线程组，一般1-2个就行
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(this.bossThread);
        // 实际工作的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            this.serverBootstrap = new ServerBootstrap();
            // 两个线程组加入进来
            this.serverBootstrap.group(bossGroup, workerGroup)
                    // 配置为nio类型
                    .channel(NioServerSocketChannel.class)
                    // 加入自己的初始化器
                    .childHandler(this.socketInitializer);

            // 服务器异步创建绑定端口
            ChannelFuture cf = this.serverBootstrap.bind(this.port).sync();

            log.info("Netty started on port: {} (TCP) with boss thread {}", this.port, this.bossThread);

            // 关闭服务器通道
            cf.channel().closeFuture().sync();
        } finally {
            // 是否线程池资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }
}
