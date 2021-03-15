package com.example.slproject.utils;

import com.example.slproject.entity.SocketBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer{
    private static TcpServer instance;
    private ServerSocket serverSocket;
    private List<SocketBean> list = new ArrayList<>();
    private ExecutorService executorService;
    private TcpServerListener tcpServerListener;
    private long TIME_OUT = 30000;//超时时间30s，30秒未收到心跳自动断开

    private TcpServer(){
    }

    public void init(int port){
        getExecutorService().submit(new SocketThread(port));
        getExecutorService().submit(new ReceiveThread());
    }

    //启动客户端监听线程和消息监听线程
    public void init(int port, int backlog){
        getExecutorService().submit(new SocketThread(port,backlog));
        getExecutorService().submit(new ReceiveThread());
    }

    //构建线程池
    private ExecutorService getExecutorService(){
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(4);
        }
        return executorService;
    }

    //单例模式
    public static TcpServer getInstance(){
        if (instance == null){
            synchronized (TcpServer.class){
                if (instance == null){
                    instance = new TcpServer();
                }
            }
        }
        return instance;
    }

    //返回记录的socket连接对象
    public List<SocketBean> getList(){
        return list;
    }

    //监听--客户端接入线程
    class SocketThread extends Thread{
        private int port;
        private int backlog;
        public SocketThread(int port) {
            this.port = port;
        }
        public SocketThread(int port, int backlog) {
            this.port = port;
            this.backlog = backlog;
        }

        @Override
        public void run() {
            try {
                if (serverSocket == null) {
                    serverSocket = new ServerSocket();
                    serverSocket.setReuseAddress(true);
                    if (backlog == 0) {
                        serverSocket.bind(new InetSocketAddress(port));
                    } else {
                        serverSocket.bind(new InetSocketAddress(port), backlog);
                    }
                }
                while (true) {
                    Socket socket = serverSocket.accept();
                    SocketBean socketBean = new SocketBean();
                    socketBean.setCurrrentTime(System.currentTimeMillis());
                    socketBean.setSocket(socket);
                    socketBean.setConnect(true);
                    socketBean.setIp(socket.getInetAddress().getHostAddress());
                    list.add(socketBean);
                    tcpServerListener.onOpen(socket);
                }
            } catch (Exception e) {
                if (e instanceof SocketException){
                    release();
                    init(8899);
                }
                e.printStackTrace();
                tcpServerListener.onFailure(e);
            }
        }
    }

    //监听--消息接收线程
    class ReceiveThread extends Thread{
        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        InputStream inputStream = list.get(i).getSocket().getInputStream();
                        int length = inputStream.available();
                        if (length != 0) {
                            byte[] bytes = new byte[length];
                            inputStream.read(bytes);
                            list.get(i).setCurrrentTime(System.currentTimeMillis());
                            String str = new String(bytes, "utf-8");
                            tcpServerListener.onMessage(bytes, list.get(i).getSocket());
                            tcpServerListener.onMessage(str, list.get(i).getSocket());
                        }else {
                            if (System.currentTimeMillis() - list.get(i).getCurrrentTime() > TIME_OUT){
                                tcpServerListener.onClosed(list.get(i).getSocket());
                                inputStream.close();
                                list.remove(i);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        tcpServerListener.onFailure(e);
                    }
                }
            }
        }
    }

    //发送消息线程
    class SendThread extends Thread{
        private String str;
        private byte[] bytes;
        private Socket socket;
        public SendThread(String str, Socket socket) {
            this.str = str;
            this.socket = socket;
        }
        public SendThread(byte[] bytes, Socket socket) {
            this.bytes = bytes;
            this.socket = socket;
        }
        @Override
        public void run() {
            for (int i=0;i<list.size();i++){
                try {
                    if (socket == list.get(i).getSocket()) {
                        if (str != null) {
                            OutputStream outputStream = list.get(i).getSocket().getOutputStream();
                            outputStream.write(str.getBytes("utf-8"));
                            break;
                        } else {
                            OutputStream outputStream = list.get(i).getSocket().getOutputStream();
                            outputStream.write(bytes);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    tcpServerListener.onFailure(e);
                }
            }
        }
    }

    //释放serverSocket
    public void release(){
        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (executorService != null){
            executorService.shutdownNow();
            executorService = null;
        }
    }

    //往某个客户端发送str
    public void send(String message, Socket socket){
        getExecutorService().submit(new SendThread(message,socket));
    }

    //往某个客户端发送byte[]
    public void send(byte[] message, Socket socket){
        getExecutorService().submit(new SendThread(message,socket));
    }

    //监听各个状态
    public void newTcpServerListener(TcpServerListener tcpServerListener){
        this.tcpServerListener = tcpServerListener;
    }

    public interface TcpServerListener {
        void onOpen(Socket socket);
        void onFailure(Exception e);
        void onMessage(String str, Socket socket);
        void onMessage(byte[] bytes, Socket socket);
        void onClosed(Socket socket);
    }
}
