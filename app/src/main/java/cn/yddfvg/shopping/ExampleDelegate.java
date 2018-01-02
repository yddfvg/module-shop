package cn.yddfvg.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import test.com.and.delegets.ShoppingDelegate;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.RestClient;

/**
 * Created by lh on 2018/1/2.
 */

public class ExampleDelegate extends ShoppingDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //对每一个控件进行的操作
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .params("","")
                .build();
    }
}
