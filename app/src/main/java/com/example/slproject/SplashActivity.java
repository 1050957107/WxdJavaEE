package com.example.slproject;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.slproject.adapter.HomeTaskSelectionAdapter;
import com.example.slproject.data.entity.TaskSelectionBean;
import com.example.slproject.data.resource.TaskSelectionResources;
import com.example.slproject.databinding.ActivityMainBinding;
import com.example.slproject.udp.UdpUtils;
import com.w.xd.mvp.base.BaseActivity;
import com.w.xd.mvp.utils.Logger;
import com.w.xd.mvp.utils.NoDoubleClickListener;
import com.w.xd.mvp.utils.SpacesItemDecoration;
import com.w.xd.mvp.widgets.ThreadManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.sleep;

public class SplashActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private HomeTaskSelectionAdapter mHomeTaskSelectionAdapter;
    private Handler mHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("HandlerLeak")
    @Override
    protected void initView() {
        /*
        设置背景初始页面
         */
        five_minutes();
        binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.wings.setBackground(getDrawable(R.drawable.home_select_background));
        binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.simple.setBackground(getDrawable(R.drawable.home_select_background));
        /*
        设置初始页面数据
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.taskselectionRe.setLayoutManager(linearLayoutManager);
        binding.taskselectionRe.addItemDecoration(new SpacesItemDecoration(10));
        mHomeTaskSelectionAdapter = new HomeTaskSelectionAdapter();
        ArrayList<TaskSelectionBean> wingsResources = TaskSelectionResources.getWingsResources();
        mHomeTaskSelectionAdapter.setTaskSelectionBean(wingsResources);
        binding.taskselectionRe.setAdapter(mHomeTaskSelectionAdapter);
        mHomeTaskSelectionAdapter.setOnClickListener(position -> mHomeTaskSelectionAdapter.setThisPosition(position));
        /*
        北京时间
         */
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                binding.time.setText((String) msg.obj);
            }
        };
        new Thread(() -> {
            while (true) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formatter.format(new Date());
                mHandler.sendMessage(mHandler.obtainMessage(100, format));
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ThreadManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                UdpUtils.getInstance().startUDPSocket();
                UdpUtils.getInstance().setUdpReceiveCallback(data -> {
                    String json = new String(data.getData(), 0, data.getLength());
                    Logger.i(json);
                    if (!json.equals(Constant.GAMESTART)) {
//                        TaskStateBean taskStateBean = new Gson().fromJson(json, TaskStateBean.class);
//                        Logger.i(taskStateBean.getMotionstatus());
//                        Logger.i(json);
                    }
                });
            }
        });

    }

    private final NoDoubleClickListener doubleClickListener = new NoDoubleClickListener() {
        @SuppressLint("NonConstantResourceId")
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                //vr设置
                case R.id.vr:
                    vr();
                    break;
                //儿童设置
                case R.id.child:
                    child();
                    break;
                //wings设置
                case R.id.wings:
                    wings();
                    break;
                //digital设置
                case R.id.digital:
                    digital();
                    break;
                //plane设置
                case R.id.plane:
                    plane();
                    break;
                //simple设置
                case R.id.simple:
                    simple();
                    break;
                //secondary设置
                case R.id.secondary:
                    secondary();
                    break;
                //difficulty设置
                case R.id.difficulty:
                    difficulty();
                    break;
                //five_minutes设置
                case R.id.five_minutes:
                    five_minutes();
                    break;
                //ten_minutes设置
                case R.id.ten_minutes:
                    ten_minutes();
                    break;
                //fifteen_minutes设置
                case R.id.fifteen_minutes:
                    fifteen_minutes();
                    break;
                //unlimited_operation设置
                case R.id.unlimited_operation:
                    unlimited_operation();
                    break;
                case R.id.gamestart:
                    UdpUtils.getInstance().sendMessage(Constant.GAMESTART);
                    break;
                case R.id.gamestop:
                    UdpUtils.getInstance().sendMessage(Constant.GAMESTOP);
                    break;
            }
        }
    };

    private int i = 0;

    //儿童设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void child() {
        i++;
        if (i % 2 == 1) {
            binding.child.setBackground(getDrawable(R.drawable.home_unselect_background));
        } else {
            binding.child.setBackground(getDrawable(R.drawable.home_select_background));
        }
    }

    //vr设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void vr() {
        i++;
        if (i % 2 == 1) {
            binding.vr.setBackground(getDrawable(R.drawable.home_unselect_background));
        } else {
            binding.vr.setBackground(getDrawable(R.drawable.home_select_background));
        }
    }

    //unlimited_operation设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void unlimited_operation() {
        binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_select_background));
    }

    //fifteen_minutes设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void fifteen_minutes() {
        binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_select_background));
    }

    //ten_minutes设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ten_minutes() {
        binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.tenMinutes.setBackground(getDrawable(R.drawable.home_select_background));
    }

    //five_minutes设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void five_minutes() {
        binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_select_background));
    }

    //difficulty设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void difficulty() {
        binding.difficulty.setBackground(getDrawable(R.drawable.home_select_background));
        binding.simple.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    //secondary设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void secondary() {
        binding.secondary.setBackground(getDrawable(R.drawable.home_select_background));
        binding.simple.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    //simple设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void simple() {
        binding.simple.setBackground(getDrawable(R.drawable.home_select_background));
        binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    //plane设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void plane() {
        mHomeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getPlaneResources());
        binding.plane.setBackground(getDrawable(R.drawable.home_select_background));
        binding.wings.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    //wings设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void wings() {
        mHomeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getWingsResources());
        binding.wings.setBackground(getDrawable(R.drawable.home_select_background));
        binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    //digital设置
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void digital() {
        mHomeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getWDigitalResources());
        binding.digital.setBackground(getDrawable(R.drawable.home_select_background));
        binding.wings.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
    }

    @Override
    protected void initListener() {
        super.initListener();
         /*
        主页监听
         */
        binding.vr.setOnClickListener(doubleClickListener);
        binding.child.setOnClickListener(doubleClickListener);
        binding.wings.setOnClickListener(doubleClickListener);
        binding.digital.setOnClickListener(doubleClickListener);
        binding.plane.setOnClickListener(doubleClickListener);
        binding.simple.setOnClickListener(doubleClickListener);
        binding.secondary.setOnClickListener(doubleClickListener);
        binding.difficulty.setOnClickListener(doubleClickListener);
        binding.fiveMinutes.setOnClickListener(doubleClickListener);
        binding.tenMinutes.setOnClickListener(doubleClickListener);
        binding.fifteenMinutes.setOnClickListener(doubleClickListener);
        binding.unlimitedOperation.setOnClickListener(doubleClickListener);
        binding.gamestart.setOnClickListener(doubleClickListener);
        binding.gamestop.setOnClickListener(doubleClickListener);
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        binding = ActivityMainBinding.bind(view);
    }
}