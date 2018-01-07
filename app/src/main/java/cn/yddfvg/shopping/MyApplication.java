package cn.yddfvg.shopping;

import android.app.Application;

import test.com.and.app.shopping;
import test.com.and.net.interceptors.DebugInterceptor;

/**
 * Created by lh on 2017/12/29.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        shopping.init(this)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
