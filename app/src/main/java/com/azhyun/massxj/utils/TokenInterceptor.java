package com.azhyun.massxj.utils;

import android.content.Intent;
import android.util.Log;

import com.azhyun.massxj.MyApplication;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.bean.ResponseResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.utils.azt.JSONTool;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * OkHttp拦截器（判断是否登录过期，并重新登录）
 */
public class TokenInterceptor implements Interceptor {

    private String cookie = "";
    private Charset UTF8 = Charset.forName("UTF-8");

    public TokenInterceptor(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
//                .header("Authorization", ""+cookie)
                .header("cookie", "JSESSIONID=" + SpUtils.get("JSESSIONID", ""))
//                .header("Authorization", "JSESSIONID="+cookie)
                .build();

        Response response = chain.proceed(request);
        if (isTokenExpired(response, request)) {//根据和服务端的约定判断token过期
           /* //同步请求方式，获取最新的Token
            String newSession = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("cookie", "JSESSIONID=" + newSession)
                    .build();
            //重新请求
            return chain.proceed(newRequest);*/
            gotoLogin();
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param
     * @param request
     * @return
     */
    private boolean isTokenExpired(Response response, final Request request) throws IOException {
        String result = null;
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        if (!HttpEngine.hasBody(response)) {
            //END HTTP
        } else if (bodyEncoded(response.headers())) {
            //HTTP (encoded body omitted)
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    //Couldn't decode the response body; charset is likely malformed.无法解码响应体；字符集可能是畸形的。
                }
            }

            if (contentLength != 0) {
                result = buffer.clone().readString(charset);
                LogUtils.d("aizhongtian_xj", "-----TokenInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + System.currentTimeMillis() + "\nbody:" + JSONTool.format(result) + "\n");
            }
        }

        ResponseResult responseResult = new Gson().fromJson(result, ResponseResult.class);
//        Log.e("---code-11111->",responseResult.toString());
//        Log.e("---code-11111->",responseResult.getResult().getCode());
        String code = responseResult.getResult().getCode();
//        Log.e("---code-->",code);
        if ("-98".equals(code)) {

            return true;
        }
        return false;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
/*
    *
     * 同步请求方式，获取最新的Token
     *
     * @return

    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        String cookie;

        LoginInfo loginInfo = LoginInfo.GETINSTANCE;
        String userName = loginInfo.getUserName();
        String password = loginInfo.getPassword();

        if (LoginInfo.GETINSTANCE.isLogin()) {
            if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {//短信登录过期
                cookie = null;

                gotoLogin();
            } else {//密码获取session
                cookie = login();
            }
        } else {//定位获取session
            cookie = getSession();
        }
        return cookie;

    }*/

    private void gotoLogin() {
        Intent intent = new Intent(MyApplication.getContext(), UserLogingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
//        Observable
//                .create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//
//                        subscriber.onNext("dd");
//                        subscriber.onCompleted();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//
//                    }
//                });
        //int a = 2 / 0;//主动抛出异常进入rxjava的error防止弹出toast
    }
/*
    private String getSession() {
        LoginInfo loginInfo = LoginInfo.GETINSTANCE;

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ServiceManager.getCookieService getCookieService = retrofit.create(ServiceManager.getCookieService.class);
        Call<LoginBDAddressResult> call = getCookieService.saveCurrentRegion("", "", "");
        try {
            retrofit2.Response<LoginBDAddressResult> response = call.execute();
            if (response.isSuccessful()) {
                LoginBDAddressResult loginBDAddressResult = response.body();
                if (!"200".equals(loginBDAddressResult.getResult().getCode())) {
                    Log.e("TAG", "拦截器-----" + loginBDAddressResult.getResult().getMessage());
                }
                loginInfo.setSession(loginBDAddressResult.getData().getJSESSIONID());
                loginInfo.setRegionId(loginBDAddressResult.getData().getRegionId());
                loginInfo.setRegionName(loginBDAddressResult.getData().getName());
                loginInfo.setLogin(false);
                return loginBDAddressResult.getData().getJSESSIONID();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

   /* private String login() {

        LoginInfo loginInfo = LoginInfo.GETINSTANCE;

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ServiceManager.RetryLoginService loginService = retrofit.create(ServiceManager.RetryLoginService.class);

        String userName = loginInfo.getUserName();
        String password = loginInfo.getPassword();

        Call<LoginResult> call = loginService.getLogin(userName, password
                , LoginInfo.GETINSTANCE.getLatitude(), LoginInfo.GETINSTANCE.getLongitude());
        try {
            retrofit2.Response<LoginResult> response = call.execute();
            if (response.isSuccessful()) {
                LoginResult loginResult = response.body();
                if (!"200".equals(loginResult.getResult().getCode())) {
                    Log.e("TAG", "拦截器-----" + loginResult.getResult().getMessage());
                    LoginInfo.GETINSTANCE.clearData();
                    gotoLogin();
                }
                loginInfo.setSession(loginResult.getData().getJSESSIONID());
                loginInfo.setRegionId(loginResult.getData().getRegionId());
                loginInfo.setRegionName(loginResult.getData().getRegionName());
                loginInfo.setId(loginResult.getData().getId());
                loginInfo.setName(loginResult.getData().getName());
                loginInfo.setCmakey(loginResult.getData().getCmakey());
                loginInfo.setAuthStatus(loginResult.getData().getAuthStatus());
                loginInfo.setIsExpert(loginResult.getData().getIsExpert());
                loginInfo.setLogin(true);
                return loginResult.getData().getJSESSIONID();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}