package cn.yddfvg.shopping;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.yddfvg.and_ec.launcher.LauncherDelegate;
import com.yddfvg.and_ec.launcher.LauncherScrollDelegate;

import test.com.and.activites.ProxyActivity;
import test.com.and.delegets.ShoppingDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public ShoppingDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

}
