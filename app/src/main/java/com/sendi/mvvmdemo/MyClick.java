package com.sendi.mvvmdemo;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/1/22.
 */

public class MyClick {

    public void onChangeAge(View view){
        Toast.makeText(view.getContext(), "click me!", Toast.LENGTH_SHORT).show();
    }

}
