package test.com.and.net.download;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.iError;
import test.com.and.net.RestCreator;

/**
 * Created by Administrator on 2018/1/7.
 */

public class DownloadHandler {

    private final String URL;
    private final WeakHashMap<String,Object> PARMS = RestCreator.getParams();
    private final IRequset iRequset;
    private final ISuccess iSuccess;
    private final IFaillure iFaillure;
    private final iError iError;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION; // 后缀
    private final String NAME;

    public DownloadHandler(String URL,
                           IRequset iRequset,
                           ISuccess iSuccess,
                           IFaillure iFaillure,
                           iError iError,
                           String DOWNLOAD_DIR,
                           String EXTENSION,
                           String NAME) {
        this.URL = URL;
        this.iRequset = iRequset;
        this.iSuccess = iSuccess;
        this.iFaillure = iFaillure;
        this.iError = iError;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void handlerDownload(){
        if (iRequset != null) {
            iRequset.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTAsk task = new SaveFileTAsk(iRequset,iSuccess);
//                            task.execute()  一个个执行
                            // 线程池的方式执行
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);
                            // 这里一定要判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (iRequset != null) {
                                    iRequset.onRequestEnd();
                                }
                            }
                        } else {
                                if (iError != null) {
                                    iError.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (iFaillure != null) {
                            iFaillure.onFailure();
                        }
                    }
                });
    }


}
