package com.w.xd.mvp.widgets;

import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


public class FragmentAdapter extends FragmentStatePagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;

    public FragmentAdapter(@NonNull FragmentManager fm, List<Fragment> lists) {
        super(fm);
        this.fragmentManager = fm;
        this.fragmentList=lists;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragmentList.get(position);
        fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

}
