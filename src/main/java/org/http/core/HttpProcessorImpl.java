package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpProcessorImpl implements HttpProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HttpProcessorImpl.class);
    private final Socket connection;

    public HttpProcessorImpl(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        logger.debug("run");
        process(connection);
    }

    @Override
    public void process(Socket socket) {
        logger.debug("process");
        try {
            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = connection.getOutputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = bufferedReader.readLine();

//            HttpRequest request = null;
//            HttpResponse response = null;

            outputStream.write(s.getBytes(UTF_8));
            outputStream.flush();

            logger.debug(outputStream.toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
