package com.example.demo.tcp;

import com.example.demo.config.SpringContext;
import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpVerticle extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start() {
        TcpMsgHandler tcpMsgHandler = SpringContext.getApplicationContext().getBean("tcpMsgHandler", TcpMsgHandler.class);

        vertx.createNetServer()
                .connectHandler(tcpMsgHandler::handle)
                .listen(8888, netServerAsyncResult -> {
                    if (netServerAsyncResult.succeeded()) {
                        log.info("TCP server started on port 8888");
                    } else {
                        log.error(netServerAsyncResult.cause().toString(), netServerAsyncResult.cause());
                    }
                });
    }
}
