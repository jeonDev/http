package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpProcessorImpl implements HttpProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HttpProcessorImpl.class);
    private final Socket connection;

    public HttpProcessorImpl(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        logger.debug("run");
        process(connection);
    }

    @Override
    public void process(Socket socket) {
        logger.debug("process");
        try {
            InputStream inputStream = connection.getInputStream();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
