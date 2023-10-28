package org.http.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessorImpl implements HttpProcessor {
    @Override
    public InputStream request(Socket socket) throws IOException {
        return null;
    }

    @Override
    public OutputStream response() throws IOException {
        return null;
    }
}
