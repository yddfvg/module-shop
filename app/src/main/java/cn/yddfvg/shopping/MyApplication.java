package cn.yddfvg.shopping;

import android.app.Application;

import test.com.and.app.Shopping;

/**
 * Created by lh on 2017/12/29.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Shopping.init(this)
                .withApiHost("http://www.baidu.com")
                .configure();
    }
}
