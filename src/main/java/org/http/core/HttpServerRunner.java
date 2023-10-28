package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Web Server Run Object
 * */
public class HttpServerRunner {
    private static final Logger logger = LoggerFactory.getLogger(HttpServerRunner.class);
    private static final WebServer webServer;

    static {
        webServer = new WebServerImpl();
    }

    /**
     * Web Server run
     * */
    public static void run() {
        logger.debug("" + webServer);
        webServer.start();
    }
}
