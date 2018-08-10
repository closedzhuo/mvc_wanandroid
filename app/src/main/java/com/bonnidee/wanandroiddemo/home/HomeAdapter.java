package com.bonnidee.wanandroiddemo.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bonnidee.wanandroiddemo.R;
import com.bonnidee.wanandroiddemo.WebView.WebViewActivity;
import com.bonnidee.wanandroiddemo.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {


    private  List<HomeBean.DataBean.DatasBean> mItemDatas;
    private String TAG = "HomeAdapter";


    public HomeAdapter(List<HomeBean.DataBean.DatasBean> mItemDatas) {
        this.mItemDatas = mItemDatas;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frg_home, null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        Log.d(TAG, "position" + i + mItemDatas.get(i).toString());
        final HomeBean.DataBean.DatasBean datasBean = mItemDatas.get(i);
        homeViewHolder.tvContent.setText(datasBean.getTitle());
        homeViewHolder.tvTitle.setText(datasBean.getAuthor());
        homeViewHolder.tvDate.setText(datasBean.getNiceDate());
        homeViewHolder.tvType.setText(datasBean.getChapterName());
        homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                intent.putExtra(Constant.MSG1, datasBean.getLink());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemDatas==null  ? 0 : mItemDatas.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_type)
        TextView tvType;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
