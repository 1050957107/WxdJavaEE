package com.example.slproject.data.entity;


public class TaskStateBean {

    /**
     * systemstatus : ok
     * seatbeltstatus : ok
     * motionstatus : ok
     * seatstatus : ok
     */

    private String systemstatus;
    private String seatbeltstatus;
    private String motionstatus;
    private String seatstatus;

    public String getSystemstatus() {
        return systemstatus;
    }

    public void setSystemstatus(String systemstatus) {
        this.systemstatus = systemstatus;
    }

    public String getSeatbeltstatus() {
        return seatbeltstatus;
    }

    public void setSeatbeltstatus(String seatbeltstatus) {
        this.seatbeltstatus = seatbeltstatus;
    }

    public String getMotionstatus() {
        return motionstatus;
    }

    public void setMotionstatus(String motionstatus) {
        this.motionstatus = motionstatus;
    }

    public String getSeatstatus() {
        return seatstatus;
    }

    public void setSeatstatus(String seatstatus) {
        this.seatstatus = seatstatus;
    }
}
