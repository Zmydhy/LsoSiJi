package com.zmy.laosiji.rxhttp;

import com.zmy.laosiji.moudle.entity.LoginEntiny;
import com.zmy.laosiji.moudle.entity.MeizhiEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Michael on 2017/8/7.
 */
//http://gank.io/api/data/福利/10/1
public interface LaoSiJiService {
    //http://carwinapi.ucheying.com/api/membership/AppLogin?appKey=6532b097-0776-42b3-b5c3-99ac84b0b9f8
    @GET("api/data/{fenlei}/{count}/{page}")
    Observable<MeizhiEntity> getMeiZhi(@Path("fenlei") String fenlei, @Path("count") int count, @Path("page") int page);


    @FormUrlEncoded
    @POST("api/membership/AppLogin?appKey=6532b097-0776-42b3-b5c3-99ac84b0b9f8")
    Observable<LoginEntiny> login2(@FieldMap Map<String, String> params);

//    url = http://www.println.net/?cate=android，其中，Query = cate
//    @GET("/")
//    Call<String> cate(@Query("cate") String cate);
}
