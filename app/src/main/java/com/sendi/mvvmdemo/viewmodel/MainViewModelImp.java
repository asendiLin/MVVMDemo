package com.sendi.mvvmdemo.viewmodel;


import com.sendi.mvvmdemo.IMainView;

import com.sendi.mvvmdemo.R;
import com.sendi.mvvmdemo.adapter.MyAdapter;
import com.sendi.mvvmdemo.bean.User;
import com.sendi.mvvmdemo.databinding.ActivityMainBinding;
import com.sendi.mvvmdemo.model.IModel;
import com.sendi.mvvmdemo.model.ModelImpl;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class MainViewModelImp implements IViewModel {

    private ActivityMainBinding mBinding;
    private MyAdapter mAdapter;
    private IModel mModel;
    private User mUser=new User("sendi",21);

    public MainViewModelImp( ActivityMainBinding binding, MyAdapter adapter) {

        this.mBinding = binding;
        this.mModel = new ModelImpl();
        this.mAdapter = adapter;

    }

    @Override
    public void initUser() {
        //初始化用户
        mUser = mModel.loadUser();
        if (mUser != null)
            mBinding.setUser(mUser);
        mBinding.setImgId(R.mipmap.bg);//设置图片资源
    }


    @Override
    public void loadUsersData() {
        //刷新列表
        List<User> userList = mModel.loadUsersData();
        mAdapter.refresh(userList);
    }

    @Override
    public void changeAge() {
        //改变年龄
        int arg = mModel.changeAge();
        mUser.setArg(arg);//此时UI会跟着改变
    }
}
