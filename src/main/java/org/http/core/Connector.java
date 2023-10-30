package org.http.core;

import java.net.Socket;

/**
 * Http Web Server Request Connector Object
 */
public interface Connector extends Runnable{
    /** Connector Start */
    void start();

    /** Connector Stop */
    void stop();
}
