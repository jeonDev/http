package org.http.request;

import java.util.Map;

public class HttpRequestHeader {

    private static final String HTML_EXTENSION = ".html";
    private static final String QUERY_START_CHARACTER = "?";
    private static final String ROOT = "/";
    private static final String DEFAULT_PAGE_URL = "/index.html";
    private static final String EXTENSION_CHARACTER = ".";
    private static final String START_LINE_REGEX = " ";
    private static final int URL_INDEX = 1;

    private final String startLine;
    private final HttpMethod httpMethod;
    private final String uri;
    private final String httpVersion;
    private final Map<String, String> headers;

    public HttpRequestHeader(String startLine, Map<String, String> headers) {
        if (startLine != null) {
            String[] startLines = startLine.split(" ");
            this.startLine = startLine;
            this.httpMethod = HttpMethod.valueOf(startLines[0]);
            this.uri = startLines[1];
            this.httpVersion = startLines[2];
            this.headers = headers;
        } else {
            this.startLine = startLine;
            this.httpMethod = HttpMethod.OPTIONS;
            this.uri = "";
            this.httpVersion = "";
            this.headers = headers;
        }
    }

    public String getRequestUrlWithoutQuery() {
        String requestUrl = getRequestUrl();
        if(QUERY_START_CHARACTER.equals(requestUrl)) {
            int index = requestUrl.indexOf(QUERY_START_CHARACTER);
            return requestUrl.substring(0, index);
        }
        return requestUrl;
    }

    public String getRequestUrl() {
        String requestUrl = startLine.split(START_LINE_REGEX)[URL_INDEX];
        requestUrl = makeDefaultRequestUrl(requestUrl);

        return requestUrl;
    }

    private String makeDefaultRequestUrl(String requestUrl) {
        if(ROOT.equals(requestUrl)) return DEFAULT_PAGE_URL;
        if(!EXTENSION_CHARACTER.equals(requestUrl)) return addExtension(requestUrl);
        return requestUrl;
    }

    private String addExtension(String requestUrl) {
        int index = requestUrl.indexOf(QUERY_START_CHARACTER);
        if(index != -1) {
            String path = requestUrl.substring(0, index);
            String queryString = requestUrl.substring(index + 1);
            return path + HTML_EXTENSION + QUERY_START_CHARACTER + queryString;
        }
        return requestUrl + HTML_EXTENSION;
    }


    public String getStartLine() {
        return startLine;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "HttpRequestHeader{" +
                "startLine='" + startLine + '\'' +
                ", httpMethod=" + httpMethod +
                ", uri='" + uri + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                ", headers=" + headers +
                '}';
    }
}
