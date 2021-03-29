package com.example.slproject.udp;

import android.util.Log;

import com.example.slproject.Constant;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpUtils {

    private static UdpUtils udpBuild;
    private static final String TAG = "UDPBuild";
    //    单个CPU线程池大小
    private static final int POOL_SIZE = 5;
    private static final int BUFFER_LENGTH = 1024;
    private byte[] receiveByte = new byte[BUFFER_LENGTH];


    private boolean isThreadRunning = false;

    private DatagramSocket client;
    private DatagramPacket receivePacket;

    private ExecutorService mThreadPool;
    private Thread clientThread;

    private OnUDPReceiveCallbackBlock udpReceiveCallback;

    private UdpUtils() {
        int cpuNumbers = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(cpuNumbers * POOL_SIZE);
    }

    public static UdpUtils getInstance() {
        if (udpBuild == null) {
            synchronized (UdpUtils.class) {
                if (udpBuild == null) {
                    udpBuild = new UdpUtils();
                }
            }
        }
        return udpBuild;
    }

    public void startUDPSocket() {
        if (client != null) return;
        try {
            client = new DatagramSocket(Constant.CLIENTPORT);
            if (receivePacket == null) {
                receivePacket = new DatagramPacket(receiveByte, BUFFER_LENGTH);
            }
            startSocketThread();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    /**
     * 开启发送数据的线程
     **/
    private void startSocketThread() {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "clientThread is running...");
                receiveMessage();
            }
        });
        isThreadRunning = true;
        clientThread.start();
    }
    /**
     * 处理接受到的消息
     **/
    private void receiveMessage() {
        while (isThreadRunning) {
            if (client != null) {
                try {
                    client.receive(receivePacket);
                } catch (IOException e) {
                    Log.i(TAG, "UDP数据包接收失败！线程停止");
                    stopUDPSocket();
                    e.printStackTrace();
                    return;
                }
            }
            if (receivePacket == null || receivePacket.getLength() == 0) {
                Log.i(TAG, "无法接收UDP数据或者接收到的UDP数据为空");
                continue;
            }
            String strReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());
            Log.i(TAG, strReceive + " from " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());
//            解析接收到的 json 信息
            if (udpReceiveCallback != null) {
                udpReceiveCallback.OnParserComplete(receivePacket);
            }
//            每次接收完UDP数据后，重置长度。否则可能会导致下次收到数据包被截断。
            if (receivePacket != null) {
                receivePacket.setLength(BUFFER_LENGTH);
            }
        }
    }
    /**
     * 停止UDP
     **/
    public void stopUDPSocket() {
        isThreadRunning = false;
        receivePacket = null;
        if (clientThread != null) {
            clientThread.interrupt();
        }
        if (client != null) {
            client.close();
            client = null;
        }
        removeCallback();
    }
    /**
     * 发送信息
     **/
    public void sendMessage(final String message) {
        if (client == null) {
            startUDPSocket();
        }
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress targetAddress = InetAddress.getByName(Constant.HOSTADDRESS);
                    DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), targetAddress, Constant.CLIENTPORT);
                    client.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public interface OnUDPReceiveCallbackBlock {
        void OnParserComplete(DatagramPacket data);
    }
    public void setUdpReceiveCallback(OnUDPReceiveCallbackBlock callback) {
        this.udpReceiveCallback = callback;
    }
    public void removeCallback(){
        udpReceiveCallback = null;
    }
}
