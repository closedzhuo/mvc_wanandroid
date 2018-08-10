package com.bonnidee.wanandroiddemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, view);
        } else {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);

            }

        }

        binView();
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract void binView();

    public abstract int getLayoutId();

}
