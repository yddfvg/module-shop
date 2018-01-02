package test.com.and.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import test.com.and.app.ConfigType;
import test.com.and.app.shopping;

/**
 * Created by ${lh} on 2018/1/2.
 */

public class RestCreator {


    public static RestService getRestService(){
        return RestServiceHold.REST_SERVICE;
    }

    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) shopping.getConfigurator().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                // 转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    // Okhttp惰性的初始化
    private static final class OkhttpHolder{
        private static final  int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                // 添加拦截器
                .build();
    }

    private static final class RestServiceHold{
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAWS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAWS;
    }
}
