package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

public class ConnectorImpl implements Connector{

    private static final Logger logger = LoggerFactory.getLogger(ConnectorImpl.class);
    private static final int DEFAULT_PORT = 8080;

    @Override
    public void start(int acceptCount, int maxThreadCount) {
        logger.debug("start(" + acceptCount + ", " + maxThreadCount + ")");
    }

    @Override
    public void accept(Socket socket) {

    }
}
