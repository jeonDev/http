package org.http.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessorImpl implements HttpProcessor {

    private final Socket socket;

    public HttpProcessorImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public InputStream request() throws IOException {
        return null;
    }

    @Override
    public OutputStream response() throws IOException {
        return null;
    }

    @Override
    public void run() {

    }
}
