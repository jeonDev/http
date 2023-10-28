package org.http.core;

import java.net.Socket;

/**
 * Http Web Server Request Connector Object
 */
public interface Connector extends Runnable{
    /** Connector Start */
    void start();

    /** Connector Run */
    void run();

    /** Request Processor Send */
    void accept(Socket socket);

}
