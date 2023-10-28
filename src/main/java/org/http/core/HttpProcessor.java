package org.http.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Http Request Processor
 * */
public interface HttpProcessor {

    InputStream request(Socket socket) throws IOException;
    OutputStream response() throws IOException;
}
