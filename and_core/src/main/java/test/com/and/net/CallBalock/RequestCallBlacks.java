package test.com.and.net.CallBalock;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.com.and.ui.loader.LoaderStyle;
import test.com.and.ui.loader.ShoppingLoader;

/**
 * Created by ${lh} on 2018/1/2.
 */

public class RequestCallBlacks implements Callback<String>{
    private final IRequset iRequset;
    private final ISuccess iSuccess;
    private final IFaillure iFaillure;
    private final iError iError;
    private final LoaderStyle iLoaderStyle;
    private static final Handler HANDLER = new Handler();

    public RequestCallBlacks(IRequset iRequset, ISuccess iSuccess, IFaillure iFaillure, test.com.and.net.CallBalock.iError iError,LoaderStyle loaderStyle) {
        this.iRequset = iRequset;
        this.iSuccess = iSuccess;
        this.iFaillure = iFaillure;
        this.iError = iError;
        this.iLoaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            // call 已经执行了
            if (call.isExecuted()) {
                if (iSuccess != null) {
                    iSuccess.onSuccess(response.body());
                }
            }
        } else {
            if (iError != null) {
                iError.onError(response.code(),response.message());
            }
        }
        stopLoader();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (iFaillure != null){
            iFaillure.onFailure();
        }
        if (iRequset != null) {
            iRequset.onRequestEnd();
        }
        stopLoader();
    }

    private void stopLoader(){
        if (iLoaderStyle != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ShoppingLoader.stopLoading();
                }
            },3000);
        }
    }

}
