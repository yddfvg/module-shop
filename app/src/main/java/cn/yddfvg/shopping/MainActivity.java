package cn.yddfvg.shopping;


import test.com.and.activites.ProxyActivity;
import test.com.and.delegets.ShoppingDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public ShoppingDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
