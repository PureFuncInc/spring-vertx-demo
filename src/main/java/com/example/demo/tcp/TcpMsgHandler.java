package com.example.demo.tcp;

import com.example.demo.RecordDao;
import com.example.demo.config.VertxConfig;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpMsgHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //    @Autowired
//    TcpClient tcpClient;
    @Autowired
    RecordDao recordDao;

    void handle(NetSocket netSocket) {
        RecordParser.newDelimited(VertxConfig.CR, netSocket)
                .handler(buffer -> {
                    // 收到訊息的入口
                    String message = buffer.toString();
                    log.info(message);

                    if (message.equals("ping")) {
                        netSocket.write("pong" + VertxConfig.CR);
                        // HOST 2 發過來就寫 DB
                        recordDao.save(message);
                    } else {
                        netSocket.write(message.toUpperCase() + VertxConfig.CR);
                    }
                });
    }
}
