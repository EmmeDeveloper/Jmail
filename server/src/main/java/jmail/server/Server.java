/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jmail.server;

import jmail.server.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server extends Thread {

    private final int port;
    private final int threadsCount;

    private final ServerSocket internalServerSocket = null;
//    private final ExecutorService threadPool;
    private final ThreadPoolExecutor threadPool;

    public Server() {
        port = 0;
        threadsCount = 0;
//        threadPool = Executors.newFixedThreadPool(10);
        threadPool = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPool.allowCoreThreadTimeOut(true);
    }

    @Override
    public void run() {
        try  {
            internalServerSocket = new ServerSocket(port);
            internalServerSocket.setReuseAddress(true);

            while (!Thread.interrupted()) {
                Socket clientSocket = internalServerSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
//                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (internalServerSocket != null) {
                try  {
                    internalServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
