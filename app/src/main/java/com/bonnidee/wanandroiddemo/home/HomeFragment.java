package com.bonnidee.wanandroiddemo.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bonnidee.wanandroiddemo.R;
import com.bonnidee.wanandroiddemo.base.BaseFragment;
import com.bonnidee.wanandroiddemo.net.HttpManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    Unbinder unbinder;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder1;
    private HomeAdapter adapter;
    private List<HomeBean.DataBean.DatasBean> mItemDatas = new ArrayList<>();
    private String TAG = "HomeFragment";
    private Object homeList;
    private int PAGE = 0;

    @Override
    protected void initData() {
        getHomeList();
    }

    @Override
    protected void binView() {
        adapter = new HomeAdapter(mItemDatas);
        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        smartRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                PAGE = 0;
                getHomeList();
                smartRefreshLayout.setEnableLoadMore(false);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PAGE++;
                getHomeList();
                smartRefreshLayout.setEnableRefresh(false);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static Fragment instance() {
        return new HomeFragment();
    }


    public void getHomeList() {
        HttpManager.httpManager().retrofit().create(HomeService.class)
                .getHomeList(PAGE)
                .compose(RxSchedulers.schedulersTransformer)
                .subscribe(new BaseObserver<HomeBean>() {

                    @Override
                    protected void onFinal() {

                    }

                    @Override
                    protected void onSuccess(HomeBean homeBean) {
                        Log.d(TAG, "onSuccess");
                        if (homeBean != null) {
                            if (homeBean.getData() == null) {
                                Log.d(TAG, "DATA为空");
                            } else {
                                Log.d(TAG, "不为空");
                            }
                            if (smartRefreshLayout.getState() == RefreshState.Refreshing) {
                                mItemDatas.clear();
                                mItemDatas.addAll(homeBean.getData().getDatas());
                                adapter.notifyDataSetChanged();
                                smartRefreshLayout.finishRefresh();
                                smartRefreshLayout.setEnableLoadMore(true);
                            } else {

                                mItemDatas.addAll(homeBean.getData().getDatas());
                                adapter.notifyDataSetChanged();
                                smartRefreshLayout.finishLoadMore();
                                smartRefreshLayout.setEnableRefresh(true);
                            }

                        } else {

                        }
                    }

                    @Override
                    protected void onFail(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }
                });
    }
}
