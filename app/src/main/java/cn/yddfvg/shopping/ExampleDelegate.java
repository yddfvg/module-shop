package cn.yddfvg.shopping;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import test.com.and.delegets.ShoppingDelegate;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.iError;
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
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("https://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("lh",response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new iError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e("lh",message);
                    }
                })
                .build().get();
    }
}
