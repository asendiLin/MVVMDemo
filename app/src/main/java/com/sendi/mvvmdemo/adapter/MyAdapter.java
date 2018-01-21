package com.sendi.mvvmdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sendi.mvvmdemo.bean.User;
import com.sendi.mvvmdemo.databinding.UserItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<User> mUserList = new ArrayList<>();

    public MyAdapter() {}

    public void refresh(List<User> userList) {
        mUserList = userList;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyHolder.create(
                LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bindTo(mUserList.get(position));
    }


    @Override
    public int getItemCount() {
        if (mUserList==null)
            return 0;
        return mUserList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private UserItemBinding mItemBinding;

        public MyHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.mItemBinding = binding;
        }

        public void bindTo(User user){
            mItemBinding.setUser(user);
            mItemBinding.executePendingBindings();

        }

        static MyHolder create(LayoutInflater inflater, ViewGroup parent) {
            UserItemBinding binding =
                    UserItemBinding.inflate(inflater, parent, false);
            return new MyHolder(binding);
        }
    }
}
