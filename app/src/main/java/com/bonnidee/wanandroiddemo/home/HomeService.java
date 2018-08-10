package com.bonnidee.wanandroiddemo.home;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {
    @GET("article/list/{page}/json")
    Observable<HomeBean> getHomeList(@Path("page") int page);

}
