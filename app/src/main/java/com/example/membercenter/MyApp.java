package com.example.membercenter;

import android.app.Application;

import com.xuexiang.xui.XUI;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }
}
