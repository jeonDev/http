package org.http.core;

import java.net.Socket;

/**
 * Http Web Server Request Connector Object
 */
public interface Connector {
    /** Connector Start */
    void start(int acceptCount, int maxThreadCount);

    /** Request Processor Send */
    void accept(Socket socket);

}
