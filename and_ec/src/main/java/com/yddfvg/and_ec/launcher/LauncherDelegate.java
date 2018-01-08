package com.yddfvg.and_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.yddfvg.and_ec.R;
import com.yddfvg.and_ec.R2;


import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import test.com.and.delegets.ShoppingDelegate;
import test.com.and.ui.launcher.ScrollLauncherTag;
import test.com.and.utils.LattePreference;
import test.com.and.utils.timer.BaseTimerTask;
import test.com.and.utils.timer.iTimerListener;

/**
 * Created by Administrator on 2018/1/7.
 */

public class LauncherDelegate extends ShoppingDelegate implements iTimerListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvtimer = null;

    private Timer timer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onChickTimerView(){
        if (timer != null) {
            timer.cancel();
            timer = null;
            checkisShowScroll();
        }
    }

    private void initTimer(){
        timer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        // delay 延时
        timer.schedule(task,0,1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 判断是否显示滑动启动页
    private void checkisShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            // 检查用户是否登录
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvtimer != null) {
                    mTvtimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount --;
                    if (mCount < 0) {
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                            checkisShowScroll();
                        }
                    }
                }
            }
        });
    }
}
