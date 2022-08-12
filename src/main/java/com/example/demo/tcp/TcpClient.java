package com.example.demo.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TcpClient {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private NetSocket netSocket = null;

    public TcpClient() {
        Vertx.vertx().createNetClient().connect(9527, "192.168.2.2", netSocketAsyncResult -> {
            if (netSocketAsyncResult.succeeded()) {
                NetSocket socket = netSocketAsyncResult.result();
                // 收到訊息要做什麼
                socket.handler(buffer -> {
                    log.info(buffer.toString());
                });

                this.netSocket = socket;
            } else {
                log.error(netSocketAsyncResult.cause().toString(), netSocketAsyncResult.cause());
            }
        });
    }

    public NetSocket getNetSocket() {
        return netSocket;
    }
}
