package com.example.demo;

import com.example.demo.config.VertxConfig;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpClient1Test {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void test() throws InterruptedException {
        Vertx.vertx().createNetClient().connect(8888, "localhost", netSocketAsyncResult -> {
            if (netSocketAsyncResult.succeeded()) {
                NetSocket socket = netSocketAsyncResult.result();
                socket.handler(buffer -> {
                    log.info(buffer.toString());
                });

                for (int i = 0; i < 1000; i++) {
                    socket.write("ping" + VertxConfig.CR);
                }
            } else {
                log.error(netSocketAsyncResult.cause().toString(), netSocketAsyncResult.cause());
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}