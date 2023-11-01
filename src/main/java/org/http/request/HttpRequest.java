package org.http.request;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    private final HttpRequestStartLine startLine;
    private final HttpHeaders headers;
    private final String body;

    private HttpRequest(HttpRequestStartLine startLine, HttpHeaders headers, String body) {
        this.startLine = startLine;
        this.headers = headers;
        this.body = body;
    }

    public static HttpRequest from(BufferedReader bufferedReader) throws IOException {
        String startLine = bufferedReader.readLine();
        if(startLine == null) throw new IllegalArgumentException("empty request");

        HttpRequestStartLine httpRequestStartLine = HttpRequestStartLine.from(startLine);
        HttpHeaders httpHeaders = HttpHeaders.from(bufferedReader);
        String body = readBody(bufferedReader, httpHeaders);

        return new HttpRequest(httpRequestStartLine, httpHeaders, body);
    }

    private static String readBody(BufferedReader bufferedReader, HttpHeaders headers) {
//        if(!headers.contains())
        int contentLength = convertIntFromContentLength(headers.get("CONTENT_LENGTH.getValue()"));
        return readData(bufferedReader, contentLength);
    }

    private static int convertIntFromContentLength(HttpHeader contentLength) {
        return Integer.parseInt(String.join("", contentLength.getValues()));
    }

    public HttpRequestStartLine getStartLine() {
        return startLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return startLine.getPath();
    }

    public boolean isGetMethod() {
        return startLine.getHttpMethod().isGet();
    }
}