package test.com.and.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ${lh} on 2018/1/2.
 *
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> parms);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> parms);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> parms);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delet(@Url String url, @QueryMap Map<String, Object> parms);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> parms);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
