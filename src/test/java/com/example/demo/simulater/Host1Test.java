package com.example.demo.simulater;

import com.example.demo.config.VertxConfig;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Host1Test {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void test() throws InterruptedException {
        Vertx.vertx().createNetClient().connect(8888, "localhost", netSocketAsyncResult -> {
            if (netSocketAsyncResult.succeeded()) {
                NetSocket socket = netSocketAsyncResult.result();
                // 收到訊息要做什麼
                socket.handler(buffer -> {
                    log.info(buffer.toString());
                });

                // 發訊息
                for (int i = 0; i < 10; i++) {
                    log.info("Host 1 發送 ping");
                    socket.write("ping" + VertxConfig.CR);
                }
            } else {
                log.error(netSocketAsyncResult.cause().toString(), netSocketAsyncResult.cause());
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
