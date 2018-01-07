package test.com.and.net.rx;

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
import test.com.and.net.RestClient;
import test.com.and.net.RestCreator;
import test.com.and.ui.loader.LoaderStyle;

/**
 * Created by ${lh} on 2018/1/2.
 */

public class RxRestClientBuilder {
    private  String URL = null;
    private static Map<String,Object> PARAMS = RestCreator.getParams();
    private  RequestBody requestBody = null;
    private LoaderStyle loaderStyle = null;
    private File file = null;
    private Context context = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url){
        this.URL = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RxRestClientBuilder loader(LoaderStyle loaderStyle, Context context){
        this.loaderStyle = loaderStyle;
        this.context = context;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.loaderStyle = LoaderStyle.BallClipRotateMultipleIndicator;
        this.context = context;
        return this;
    }

    public final RxRestClientBuilder file(File file){
       this.file = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.file = new File(file);
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(URL,PARAMS,requestBody,loaderStyle,context,file);
    }
}
