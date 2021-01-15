package com.azhyun.massxj.utils;

import android.annotation.SuppressLint;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //   测试：   http://testvcs.51zhongzi.com:8050/
    //   调试：   http://192.168.0.112:8080/vcs/
    //   调试：   http://192.168.0.119:8080/vcs/
    //   正式：   https://msi-county.51zhongzi.com/
    //   测试     http://192.168.0.120:8080/vcs/
    //   测试     http://192.168.0.172:8080/vcs/
    //   测试黄美青      http://test-hmq.51zhongzi.com:8088/vcs/
    //   测试李钊     http://lz.51test.com:8090/vcs/
//    public static final String API_BASE_URL = "";
//    public static final String API_BASE_URL = "http://lz.51test.com:8066/land/";//李招
    public static final String API_BASE_URL = Constant.BASE_URL;//李招

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 5000;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);


    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    @SuppressLint("LongLogTag")
    public static <S> S createService(Class<S> serviceClass, final String cookie) {
        //添加一个log拦截器,打印所有的log
        if (!httpClient.interceptors().isEmpty())
            httpClient.interceptors().clear();
        httpClient.addInterceptor(new TokenInterceptor(cookie));
        httpClient.addInterceptor(new LoggingInterceptor()); //日志,所有的请求响应度看到
            /*httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("cookie", "JSESSIONID=" + cookie)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });*/

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}