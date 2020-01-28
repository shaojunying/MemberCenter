package com.example.membercenter.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.membercenter.R;

public class StaticMethods {

    public static void showMessage(Context context, String url, ImageView target, int defaultImage){
        RequestOptions options = new RequestOptions()
                .placeholder(defaultImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(options).into(target);

    }


}
