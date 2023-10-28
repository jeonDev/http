package org.http.core;

import java.net.Socket;

/**
 * Http Request Processor
 * */
public interface HttpProcessor extends Runnable {
    void process(Socket connection);
}
