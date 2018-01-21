package com.sendi.mvvmdemo.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sendi.mvvmdemo.BR;

/**
 * Created by Administrator on 2018/1/20.
 */

public class User extends BaseObservable{
    private String name;
    private int arg;

    public User(String name, int arg) {
        this.name = name;
        this.arg = arg;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }
    @Bindable
    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
        notifyPropertyChanged(BR.arg);
    }
}
