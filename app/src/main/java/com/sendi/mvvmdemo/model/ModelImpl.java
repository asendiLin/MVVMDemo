package com.sendi.mvvmdemo.model;

import com.sendi.mvvmdemo.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class ModelImpl implements IModel {
    @Override
    public List<User> loadUsersData() {
        //模拟从网络加载数据
        List<User>userList=new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            userList.add(new User("sen",21+i));
        }

        return userList;
    }

    @Override
    public int changeAge() {
        //模拟从网络更改信息
        return 5;
    }

    @Override
    public User loadUser() {
        return new User("sendi",21);
    }
}
