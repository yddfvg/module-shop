package test.com.and.net;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.RequestCallBlacks;
import test.com.and.net.CallBalock.iError;
import test.com.and.ui.loader.LoaderStyle;
import test.com.and.ui.loader.ShoppingLoader;

/**
 * Created by lh on 2018/1/2.
 *  使用建筑者模式
 */

public class RestClient {
    // RestClient在每次要去build的时候都会去生成一个新的私立

    private final String URL;
    private final WeakHashMap<String,Object> PARMS = RestCreator.getParams();
    private final IRequset iRequset;
    private final ISuccess iSuccess;
    private final IFaillure iFaillure;
    private final iError iError;
    private final RequestBody requestBody;
    private final LoaderStyle mLoaderStyle;
    private final File file;
    private final Context mContext;

    public RestClient(String URL, Map<String, Object> parms,
                      IRequset iRequset,
                      ISuccess iSuccess,
                      IFaillure iFaillure,
                      test.com.and.net.CallBalock.iError iError,
                      RequestBody requestBody,LoaderStyle mLoaderStyle,Context mContext,File file) {
        this.URL = URL;
        PARMS.putAll(parms);
        this.iRequset = iRequset;
        this.iSuccess = iSuccess;
        this.iFaillure = iFaillure;
        this.iError = iError;
        this.requestBody = requestBody;
        this.mLoaderStyle = mLoaderStyle;
        this.mContext = mContext;
        this.file = file;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (iRequset != null) {
            iRequset.onRequestStart();
        }

        if (mLoaderStyle != null) {
            ShoppingLoader.showLoading(mContext,mLoaderStyle);
        }

        switch (method) {
            case GET:
                call = service.get(URL,PARMS);
                break;
            case POST:
                call = service.post(URL,PARMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, requestBody);
                break;
            case PUT:
                call = service.put(URL,PARMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,requestBody);
                break;
            case UPLOAD:
               final RequestBody requestBody =
                       RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),file);
               final MultipartBody.Part body =
                       MultipartBody.Part.createFormData("file",file.getName(),requestBody);
               call = RestCreator.getRestService().upload(URL,body);
                break;
            case DELETE:
                call = service.delet(URL,PARMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallBlacks(
                iRequset,
                iSuccess,
                iFaillure,
                iError,
                mLoaderStyle
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        if (requestBody == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARMS.isEmpty()){
                throw new RuntimeException("parms must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put(){
        if (requestBody == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARMS.isEmpty()){
                throw new RuntimeException("parms must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
