package test.com.and.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import test.com.and.net.CallBalock.IFaillure;
import test.com.and.net.CallBalock.IRequset;
import test.com.and.net.CallBalock.ISuccess;
import test.com.and.net.CallBalock.RequestCallBlacks;
import test.com.and.net.CallBalock.iError;
import test.com.and.net.HttpMethod;
import test.com.and.net.RestClientBuilder;
import test.com.and.net.RestCreator;
import test.com.and.net.RestService;
import test.com.and.net.download.DownloadHandler;
import test.com.and.ui.loader.LoaderStyle;
import test.com.and.ui.loader.ShoppingLoader;

/**
 * Created by lh on 2018/1/2.
 *  使用建筑者模式
 */

public class RxRestClient {
    // RestClient在每次要去build的时候都会去生成一个新的私立

    private final String URL;
    private final WeakHashMap<String,Object> PARMS = RestCreator.getParams();
    private final RequestBody requestBody;
    private final LoaderStyle mLoaderStyle;
    private final File file;
    private final Context mContext;

    public RxRestClient(String URL,
                        Map<String, Object> parms,
                        RequestBody requestBody,
                        LoaderStyle mLoaderStyle,
                        Context mContext,
                        File file
                    ) {
        this.URL = URL;
        PARMS.putAll(parms);
        this.requestBody = requestBody;
        this.mLoaderStyle = mLoaderStyle;
        this.mContext = mContext;
        this.file = file;
    }

    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method){
        final RxRestService service = RestCreator.RxgetRestService();
        Observable<String> observable = null;

        if (mLoaderStyle != null) {
            ShoppingLoader.showLoading(mContext,mLoaderStyle);
        }

        switch (method) {
            case GET:
                observable = service.get(URL,PARMS);
                break;
            case POST:
                observable = service.post(URL,PARMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, requestBody);
                break;
            case PUT:
                observable = service.put(URL,PARMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL,requestBody);
                break;
            case UPLOAD:
               final RequestBody requestBody =
                       RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),file);
               final MultipartBody.Part body =
                       MultipartBody.Part.createFormData("file",file.getName(),requestBody);
                observable = service.upload(URL,body);
                break;
            case DELETE:
                observable = service.delet(URL,PARMS);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get(){
       return request(HttpMethod.GET);
    }

    public final Observable<String> post(){
        if (requestBody == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARMS.isEmpty()){
                throw new RuntimeException("parms must be null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put(){
        if (requestBody == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARMS.isEmpty()){
                throw new RuntimeException("parms must be null");
            }
            return request(HttpMethod.PUT_RAW);
        }

    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download(){
//        return new DownloadHandler(URL,iRequset,iSuccess,iFaillure,iError,download_dir,extension,name).handlerDownload();
            final Observable<ResponseBody> responseBodyObservable =
                    RestCreator.RxgetRestService().download(URL,PARMS);
            return responseBodyObservable;
    }
}
