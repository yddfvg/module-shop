package cn.yddfvg.shopping;

import android.app.Application;

import test.com.and.app.shopping;

/**
 * Created by lh on 2017/12/29.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        shopping.init(this)
                .withApiHost("https://www.baidu.com/")
                .configure();
    }
}
