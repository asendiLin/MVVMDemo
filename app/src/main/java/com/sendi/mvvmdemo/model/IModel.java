package com.sendi.mvvmdemo.model;

import com.sendi.mvvmdemo.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public interface IModel {
    /**
     * 加载数据
     */
    List<User> loadUsersData();

    /**
     * 更改年龄
     */
    int changeAge();

    /**
     * 加载用户信息
     */
    User loadUser();
}
