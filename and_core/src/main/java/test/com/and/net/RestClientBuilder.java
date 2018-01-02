package test.com.and.net;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.iError;

/**
 * Created by ${lh} on 2018/1/2.
 */

public class RestClientBuilder {
    private  String URL;
    private static Map<String,Object> PARAMS = RestCreator.getParams();
    private  IRequset iRequset;
    private  ISuccess iSuccess;
    private  IFaillure iFaillure;
    private  test.com.and.net.CallBalock.iError iError;
    private  RequestBody requestBody;

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

    public final RestClient build(){
        return new RestClient(URL,PARAMS,iRequset,iSuccess,iFaillure,iError,requestBody);
    }
}
