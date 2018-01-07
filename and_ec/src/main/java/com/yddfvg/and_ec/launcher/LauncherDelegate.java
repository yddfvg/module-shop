package com.yddfvg.and_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yddfvg.and_ec.R;

import butterknife.BindView;
import test.com.and.delegets.ShoppingDelegate;

/**
 * Created by Administrator on 2018/1/7.
 */

public class LauncherDelegate extends ShoppingDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
