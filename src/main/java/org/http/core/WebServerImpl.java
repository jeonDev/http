package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebServerImpl implements WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServerImpl.class);
    private final Connector connector;
    private static final int DEFAULT_ACCEPT_COUNT = 1;
    private static final int DEFAULT_MAX_THREAD_COUNT = 10;
    private final int acceptCount;
    private final int maxThreadCount;

    {
        acceptCount = DEFAULT_ACCEPT_COUNT;
        maxThreadCount = DEFAULT_MAX_THREAD_COUNT;
        connector = new ConnectorImpl(acceptCount, maxThreadCount);
    }

    @Override
    public void start() {
        logger.debug("Web Server Start : " + connector);
        connector.start();

        try {
            System.in.read();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            logger.info("Web Server Stop.");
            connector.stop();
        }
    }
}
