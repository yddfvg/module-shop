package test.com.and.net;

import android.support.v7.app.AlertDialog;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.RequestCallBlacks;
import test.com.and.net.CallBalock.iError;

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

    public RestClient(String URL, Map<String, Object> parms,
                      IRequset iRequset,
                      ISuccess iSuccess,
                      IFaillure iFaillure,
                      test.com.and.net.CallBalock.iError iError,
                      RequestBody requestBody) {
        this.URL = URL;
        PARMS.putAll(parms);
        this.iRequset = iRequset;
        this.iSuccess = iSuccess;
        this.iFaillure = iFaillure;
        this.iError = iError;
        this.requestBody = requestBody;
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

        switch (method) {
            case GET:
                call = service.get(URL,PARMS);
                break;
            case POST:
                call = service.post(URL,PARMS);
                break;
            case PUT:
                call = service.put(URL,PARMS);
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
                iError
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
