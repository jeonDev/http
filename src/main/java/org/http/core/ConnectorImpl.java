package org.http.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectorImpl implements Connector {

    private static final Logger logger = LoggerFactory.getLogger(ConnectorImpl.class);
    private static final int DEFAULT_PORT = 8080;
    private static final int DEFAULT_ACCEPT_COUNT = 100;
    private final ExecutorService executorService;
    private final ServerSocket serverSocket;
    private boolean stopped;

    public ConnectorImpl(int port, int acceptCount, ExecutorService executorService) {
        this.executorService = executorService;
        this.serverSocket = createServerSocket(port, acceptCount);
    }

    public ConnectorImpl(int acceptCount, int maxThreadCount) {
        this(DEFAULT_PORT, acceptCount, Executors.newFixedThreadPool(maxThreadCount));
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
        this.stopped = false;
    }

    @Override
    public void stop() {
        this.stopped = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        while(!stopped) {
            connect();
        }
    }

    private void connect() {
        try {
            // accept() 메소드는 클라이언트가 연결 요청하기 전까지 블로킹 -> Thread가 대기 상태
            process(serverSocket.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(Socket connection) {
        if(connection == null) {
            return;
        }
        HttpProcessor processor = new HttpProcessorImpl(connection);
        executorService.execute(processor);
    }

    private ServerSocket createServerSocket(int port, int acceptCount) {
        try {
            int checkedPort = checkPort(port);
            int checkedAcceptCount = checkAcceptCount(acceptCount);
            return new ServerSocket(checkedPort, checkedAcceptCount);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private int checkPort(int port) {
        int MIN_PORT = 1;
        int MAX_PORT = 65535;
        return (port > MIN_PORT && port < MAX_PORT) ? port : DEFAULT_PORT;
    }

    private int checkAcceptCount(int acceptCount) {
        return Math.max(acceptCount, DEFAULT_ACCEPT_COUNT);
    }
}
