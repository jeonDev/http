package org.http.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Http Request Processor
 * */
public interface HttpProcessor extends Runnable {

    InputStream request() throws IOException;
    OutputStream response() throws IOException;
}
