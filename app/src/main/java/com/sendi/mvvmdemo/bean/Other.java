package com.sendi.mvvmdemo.bean;

import android.databinding.ObservableField;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Other {
    //效果和User的@Bindable+notifyPropertyChanged(BR.xxx)相同
    //通过set改变数据进而布局中绑定的数据跟着改变
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();
}
