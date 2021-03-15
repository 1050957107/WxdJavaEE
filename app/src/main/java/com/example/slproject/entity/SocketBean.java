package com.example.slproject.entity;

import java.net.Socket;

public class SocketBean {
    private String mic;
    private String ip;
    private Socket socket;
    private long currrentTime;
    private boolean isConnect;

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public long getCurrrentTime() {
        return currrentTime;
    }

    public void setCurrrentTime(long currrentTime) {
        this.currrentTime = currrentTime;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
