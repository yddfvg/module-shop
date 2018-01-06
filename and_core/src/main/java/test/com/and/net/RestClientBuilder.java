package test.com.and.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.iError;
import test.com.and.ui.loader.LoaderStyle;

/**
 * Created by ${lh} on 2018/1/2.
 */

public class RestClientBuilder {
    private  String URL = null;
    private static Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequset iRequset = null;
    private  ISuccess iSuccess = null;
    private  IFaillure iFaillure = null;
    private  iError iError = null;
    private  RequestBody requestBody = null;
    private LoaderStyle loaderStyle = null;
    private File file = null;
    private Context context = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder onRequest(IRequset iRequset){
        this.iRequset = iRequset;
        return this;
    }

    public final RestClientBuilder url(String url){
        this.URL = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.iSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFaillure iFaillure){
        this.iFaillure = iFaillure;
        return this;
    }

    public final RestClientBuilder error(iError iError){
        this.iError = iError;
        return this;
    }

    public final RestClientBuilder loader(LoaderStyle loaderStyle,Context context){
        this.loaderStyle = loaderStyle;
        this.context = context;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.loaderStyle = LoaderStyle.BallClipRotateMultipleIndicator;
        this.context = context;
        return this;
    }

    public final RestClientBuilder file(File file){
       this.file = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.file = new File(file);
        return this;
    }

    public final RestClient build(){
        return new RestClient(URL,PARAMS,iRequset,iSuccess,iFaillure,iError,requestBody,loaderStyle,context,file);
    }
}
