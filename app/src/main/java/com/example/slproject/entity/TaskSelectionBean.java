package com.example.slproject.entity;

public class TaskSelectionBean {
    private int drawable;
    private String title;

    public TaskSelectionBean(int drawable, String title) {
        this.drawable = drawable;
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
