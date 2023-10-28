package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServerImpl implements WebServer {
    private static final Logger logger = LoggerFactory.getLogger(WebServerImpl.class);
    private final Connector connector;
    private static final int DEFAULT_ACCEPT_COUNT = 1;
    private static final int DEFAULT_MAX_THREAD_COUNT = 10;
    private final int acceptCount;
    private final int maxThreadCount;

    {
        connector = new ConnectorImpl();
        acceptCount = DEFAULT_ACCEPT_COUNT;
        maxThreadCount = DEFAULT_MAX_THREAD_COUNT;
    }

    @Override
    public void start() {
        logger.debug("Web Server Start : " + connector);
        connector.start(acceptCount, maxThreadCount);
    }
}
