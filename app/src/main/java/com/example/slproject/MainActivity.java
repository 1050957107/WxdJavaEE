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
import com.example.slproject.databinding.ActivityMainBinding;
import com.example.slproject.entity.TaskSelectionBean;
import com.example.slproject.entity.TaskSelectionResources;
import com.example.slproject.utils.SpacesItemDecoration;
import com.example.slproject.widgets.NoDoubleClickListener;
import com.w.xd.mvp.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private HomeTaskSelectionAdapter homeTaskSelectionAdapter;
    private ArrayList<TaskSelectionBean> wingsResources;
    private Handler handler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("HandlerLeak")
    @Override
    protected void initView() {
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
        binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_select_background));
        binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.wings.setBackground(getDrawable(R.drawable.home_select_background));
        binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
        binding.simple.setBackground(getDrawable(R.drawable.home_select_background));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.taskselectionRe.setLayoutManager(linearLayoutManager);
        binding.taskselectionRe.addItemDecoration(new SpacesItemDecoration(10));
        homeTaskSelectionAdapter = new HomeTaskSelectionAdapter();
        wingsResources = TaskSelectionResources.getWingsResources();
        homeTaskSelectionAdapter.setTaskSelectionBean(wingsResources);
        binding.taskselectionRe.setAdapter(homeTaskSelectionAdapter);
        homeTaskSelectionAdapter.setOnClickListener(new HomeTaskSelectionAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                homeTaskSelectionAdapter.setThisPosition(position);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                binding.time.setText((String) msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = formatter.format(new Date());
                    handler.sendMessage(handler.obtainMessage(100, str));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private int i=0;

    private NoDoubleClickListener doubleClickListener = new NoDoubleClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.vr:
                    i++;
                    if (i%2==1){
                        binding.vr.setBackground(getDrawable(R.drawable.home_unselect_background));
                    }else {
                        binding.vr.setBackground(getDrawable(R.drawable.home_select_background));
                    }
                    break;
                case R.id.child:
                    i++;
                    if (i%2==1){
                        binding.child.setBackground(getDrawable(R.drawable.home_unselect_background));
                    }else {
                        binding.child.setBackground(getDrawable(R.drawable.home_select_background));
                    }
                    break;
                case R.id.wings:
                    homeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getWingsResources());
                    binding.wings.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.digital:
                    homeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getWDigitalResources());
                    binding.digital.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.wings.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.plane.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.plane:
                    homeTaskSelectionAdapter.setTaskSelectionBean(TaskSelectionResources.getPlaneResources());
                    binding.plane.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.wings.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.digital.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.simple:
                    binding.simple.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.secondary:
                    binding.secondary.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.simple.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.difficulty.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.difficulty:
                    binding.difficulty.setBackground(getDrawable(R.drawable.home_select_background));
                    binding.simple.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.secondary.setBackground(getDrawable(R.drawable.home_unselect_background));
                    break;
                case R.id.five_minutes:
                    binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_select_background));
                    break;
                case R.id.ten_minutes:
                    binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.tenMinutes.setBackground(getDrawable(R.drawable.home_select_background));
                    break;
                case R.id.fifteen_minutes:
                    binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_select_background));
                    break;
                case R.id.unlimited_operation:
                    binding.tenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fifteenMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.fiveMinutes.setBackground(getDrawable(R.drawable.home_unselect_background));
                    binding.unlimitedOperation.setBackground(getDrawable(R.drawable.home_select_background));
                    break;
            }
        }
    };

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void bindingView(View view) {
        super.bindingView(view);
        binding = ActivityMainBinding.bind(view);
    }
}