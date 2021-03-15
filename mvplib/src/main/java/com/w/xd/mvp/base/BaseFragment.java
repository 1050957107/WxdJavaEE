package com.w.xd.mvp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.m.k.mvp.R;
import com.trello.rxlifecycle4.components.support.RxFragment;
import com.w.xd.mvp.utils.Logger;
import com.w.xd.mvp.utils.TimeUtils;

import java.math.BigDecimal;


public abstract class BaseFragment extends RxFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getLayoutId() == 0){
            return null;
        }
        View v  = inflater.inflate(getLayoutId(),container,false);

        String viewClassName = v.getClass().getName();

        if(viewClassName.equals(RelativeLayout.class.getName())
                || viewClassName.equals(ConstraintLayout.class.getName())
                || viewClassName.equals(FrameLayout.class.getName())
                || viewClassName.equals(ContentFrameLayout.class.getName())){

            return v;
        }else{

            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.addView(v);
            frameLayout.setTag("wrap");

            return frameLayout;
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(view.getTag() != null){
             bindView(((ViewGroup)view).getChildAt(0));
        }else{
            bindView(view);
        }

        initView();


    }

    protected void bindView(View view){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int getLayoutId();

    protected  void initView(){ }



    public  <T extends View> T findViewById(@IdRes int id){
        return getView().findViewById(id);
    }


    protected void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int id){
        Toast.makeText(getContext(),id,Toast.LENGTH_SHORT).show();
    }


    public boolean isNeedAnimation(){
        return true;
    }

    public int  getEnterAnimation(){

        return R.anim.common_page_right_in;
    }

    public int  getExitAnimation(){
        return R.anim.common_page_left_out;
    }
    public int  getPopEnterAnimation(){
        return R.anim.common_page_left_in;
    }
    public int  getPopExitAnimation(){
        return R.anim.common_page_right_out;
    }
    public boolean isNeedAddToBackStack(){
        return true;
    }

    public Action getActionFroPreFragment(){
        return Action.HIDE;
    }

    /**
     * 对上一个fragment 如何进行处理
     */
    public enum Action{
        NONE,HIDE,DETACH,REMOVE
    }


    public void showfitsSystemWindows() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
