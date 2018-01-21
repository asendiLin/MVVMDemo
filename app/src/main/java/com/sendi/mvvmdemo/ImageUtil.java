package com.sendi.mvvmdemo;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/1/21.
 */

public class ImageUtil {

    @BindingAdapter({"image"})
    public static void imageLoader(ImageView imageView,int imageId){
        imageView.setImageResource(imageId);
    }
}
