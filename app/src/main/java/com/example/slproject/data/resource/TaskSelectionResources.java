package com.example.slproject.data.resource;


import com.example.slproject.R;
import com.example.slproject.data.entity.TaskSelectionBean;

import java.util.ArrayList;

public class TaskSelectionResources {
    public static ArrayList<TaskSelectionBean> getWingsResources(){
        ArrayList<TaskSelectionBean> taskSelectionBeans = new ArrayList<>();
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"柏林空战"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"柯颂之战"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"无敌模式"));
        return taskSelectionBeans;
    }
    public static ArrayList<TaskSelectionBean> getWDigitalResources(){
        ArrayList<TaskSelectionBean> taskSelectionBeans = new ArrayList<>();
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"自由之战"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"飞跃小镇"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"空中格斗"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"穿越桥梁"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"河面飞行"));
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"空中狩猎"));
        return taskSelectionBeans;
    }
    public static ArrayList<TaskSelectionBean> getPlaneResources(){
        ArrayList<TaskSelectionBean> taskSelectionBeans = new ArrayList<>();
        taskSelectionBeans.add(new TaskSelectionBean(R.mipmap.ic_launcher,"自由飞行"));
        return taskSelectionBeans;
    }
}
