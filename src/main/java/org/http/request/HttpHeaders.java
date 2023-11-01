package org.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class HttpHeaders {
    private Map<String, HttpHeader> headers = new LinkedHashMap<>();
    private static final String COLON_LETTER = ":";

    public HttpHeaders() {
    }

    private HttpHeaders(Map<String, HttpHeader> headers) {
        this.headers = headers;
    }

    public static HttpHeaders from(BufferedReader bufferedReader) {
        return new HttpHeaders(readAllHeaders(bufferedReader));
    }

    public static HttpHeaders of(HttpHeader... httpHeaders) {
        Map<String, HttpHeader> headers = Arrays.stream(httpHeaders)
                .collect(Collectors.toMap(HttpHeader::getHttpHeaderType,
                        httpHeader -> httpHeader,
                        (key, value) -> value,
                        LinkedHashMap::new));

        return new HttpHeaders(headers);
    }

    private static Map<String, HttpHeader> readAllHeaders(BufferedReader bufferedReader) throws IOException {
        Map<String, HttpHeader> headers = new LinkedHashMap<>();
        while(true) {
            String line = bufferedReader.readLine();
            if(line.equals("")) {
                break;
            }
            List<String> header = parseHeader(line);
            String headerType = removeBlank(header.get(0));
            String headerValue = removeBlank(header.get(1));
            String httpHeaderType = HttpHeaderType.of(headerType);
            headers.put(httpHeaderType, HttpHeader.of(httpHeaderType, headerValue));
        }

        return headers;
    }

    private static List<String> parseHeader(String line) {
        List<String> header = List.of(line.split(COLON_LETTER));
        validateHeader(header);
        return header;
    }

    private static void validateHeader(List<String> header) {
        if(header.size() < 2) {
            throw new IllegalArgumentException("Request information is invalid.");
        }
    }

    public boolean contains(final String httpHeaderType) {
        return headers.containsKey(httpHeaderType);
    }

    public HttpHeader get(final String httpHeaderType) {
        return headers.get(httpHeaderType);
    }

    public Set<String> keySet() {
        return headers.keySet();
    }

    public void put(String httpHeaderType, HttpHeader httpHeader) {
        headers.put(httpHeaderType, httpHeader);
    }

    public void add(HttpHeader httpHeader) {
        headers.put(httpHeader.getHttpHeaderType(), httpHeader);
    }
    private static String removeBlank(String s) {
    }
}
