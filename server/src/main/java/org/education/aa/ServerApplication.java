package org.education.aa;

import org.education.aa.config.SocketServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * 启动类
 *
 * @author fbin
 */
@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    @Resource
    private SocketServer socketServer;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        socketServer.start();
    }
}
