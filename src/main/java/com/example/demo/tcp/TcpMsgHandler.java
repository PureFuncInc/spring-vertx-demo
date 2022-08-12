package com.example.demo.tcp;

import com.example.demo.config.VertxConfig;
import com.example.demo.service.SampleService;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TcpMsgHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SampleService sampleService;

    public TcpMsgHandler(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    void handle(NetSocket netSocket) {
        RecordParser.newDelimited(VertxConfig.CR, netSocket)
                .handler(buffer -> {
                    String message = buffer.toString();
                    log.info(message);

                    netSocket.write(sampleService.process(message) + VertxConfig.CR);
                });
    }
}
