package com.zmy.laosiji.Interface;

import com.zmy.laosiji.Entity.MeizhiEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Michael on 2017/8/7.
 */
//http://gank.io/api/data/福利/10/1
public interface LaoSiJiService {
    @GET("api/data/{fenlei}/{count}/{page}")
    Observable<MeizhiEntity> getMeiZhi(@Path("fenlei") String fenlei,@Path("count") int count ,@Path("page") int page);

    @GET("api/data/{fenlei}/{count}/{page}")
    Call<MeizhiEntity> getMeiZhis(@Path("fenlei") String fenlei, @Path("count") int count , @Path("page") int page);


}
