package com.example.slproject.utils;

import android.util.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {
    private static SocketService mInstance;
    private static ServerSocket mServerSocket;
    private static Socket socket;
    public static SocketService getInstance() {
        if (mInstance==null){
            synchronized (SocketService.class){
                mInstance=new SocketService();
            }
        }
        return mInstance;
    }
    public void sendMsg(String msg) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("TAG","-------------run----------------");
                    ServerSocket serverSocket =null;
                    serverSocket= new ServerSocket(8080);
                    socket = serverSocket.accept();
                    // 获得io流
                    PrintStream out = new PrintStream(socket.getOutputStream());
                    out.println(msg);
                    out.close();
                    serverSocket.close();
                    socket.close();
                    Log.e("TAG","-------------stop----------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
