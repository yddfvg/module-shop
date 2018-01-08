package cn.yddfvg.shopping;


import com.yddfvg.and_ec.launcher.LauncherDelegate;
import com.yddfvg.and_ec.launcher.LauncherScrollDelegate;

import test.com.and.activites.ProxyActivity;
import test.com.and.delegets.ShoppingDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public ShoppingDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }

}
