package com.example.slproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slproject.utils.SocketService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                SocketService.getInstance().sendMsg("客户端向服务端发送消息");
                                Log.e("TAG","客户端向服务端发送消息");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Socket socket = null;
                        InetAddress addr = null;
                        try {
                            addr = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        String ip=addr.getHostAddress();//获得本机IP
                        try {
                            socket = new Socket(ip, 8080);
                            // 获得io流
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                            Log.e("TAG","通过服务器得到的："+in.readLine());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}