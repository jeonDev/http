package org.http.core;

import org.http.request.HttpRequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpProcessorImpl implements HttpProcessor {
    private static final Logger logger = LoggerFactory.getLogger(HttpProcessorImpl.class);
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String HEADER_DELIMITER = ": ";
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

            // 1. Request
            // 1-1. Http Header Setting
            HttpRequestHeader httpRequestHeader = makeHttpRequestHeader(bufferedReader);
            String requestUrl = httpRequestHeader.getRequestUrl();
            logger.debug(httpRequestHeader.toString());
            logger.debug(requestUrl);
            // 1-2. Http Body Setting

            // 2. Process

            // 3. Response
            // 3-1. Http Header Setting

            // 3-2. Http Body Setting

            outputStream.write("s.getBytes(UTF_8)".getBytes());
            outputStream.flush();

            logger.debug(outputStream.toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


    private HttpRequestHeader makeHttpRequestHeader(BufferedReader bufferedReader) throws IOException {
        String httpStartLine = bufferedReader.readLine();
        Map<String, String> httpHeaderLines = makeHttpHeaderLines(bufferedReader);

        return new HttpRequestHeader(httpStartLine, httpHeaderLines);
    }

    private Map<String, String> makeHttpHeaderLines(BufferedReader bufferedReader) throws IOException{
        Map<String, String> httpHeaderLines = new HashMap<>();

        String line;
        while((line = bufferedReader.readLine()) != null) {
             if(line.isBlank()) break;

             String[] header = line.split(HEADER_DELIMITER);
             httpHeaderLines.put(header[KEY_INDEX], header[VALUE_INDEX]);
        }
        return httpHeaderLines;
    }
}
