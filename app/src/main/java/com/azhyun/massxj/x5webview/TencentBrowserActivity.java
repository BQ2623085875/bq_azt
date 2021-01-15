package com.azhyun.massxj.x5webview;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.azhyun.massxj.R;
import com.azhyun.massxj.activity.UserLogingActivity;
import com.azhyun.massxj.bean.SharefoResult;
import com.azhyun.massxj.bean.SpUtils;
import com.azhyun.massxj.utils.HttpService;
import com.azhyun.massxj.utils.ServiceGenerator;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TencentBrowserActivity extends AppCompatActivity {
    private ProgressWebView webView;
    private ProgressBar progressBar;
    private ImageView bank;
    private TextView title;
    private String titleString = "";
    private TextView name;
    private TextView lai;
    private TextView shi;
    private String shistr;
    private String namestr;
    private String laistr;

//private String url = "http://3g.qq.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //intent
        String url = getIntent().getStringExtra("url");
        shistr = getIntent().getStringExtra("shi");
        namestr = getIntent().getStringExtra("name");
        laistr = getIntent().getStringExtra("lai");
        titleString = getIntent().getStringExtra("title");

        initView();
        Log.e("-->>Url", url);

        loadUrl(url);
    }

    private void initView() {
        webView = (ProgressWebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        bank = (ImageView) findViewById(R.id.bank);
        name = (TextView) findViewById(R.id.name);
        lai = (TextView) findViewById(R.id.laiyuan);
        shi = (TextView) findViewById(R.id.shijian);
        title = (TextView) findViewById(R.id.title);

        shi.setText(shistr);
        lai.setText("来源:  " + laistr);
        name.setText(namestr);

        title.setText(titleString);
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(18);

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView != null && webView.canGoBack()) {
                    webView.goBack();

                } else {
                    finish();
                }
            }
        });
        initProgressBar();
    }

    private void initProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);// new
        // ProgressBar(getApplicationContext(),
        // null,
        // android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        progressBar.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
    }

    private void loadUrl(String url) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");

        webSettings.setBlockNetworkImage(true);//设置不能访问网络图片
        webSettings.setSupportZoom(true);//开启网页的缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);//设置网页缩放至手机大小
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSInteface(), "mobile");
        webView.setWebChromeClient(new WebChromeClient());//支持特殊javascript
        webSettings.setDomStorageEnabled(true);//重点是这个设置
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//对H5支持
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                Log.i("打印日志", "网页加载失败");

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有证书
            }
        });
        //进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    public class JSInteface {
        private int articleId = 0;
        private String token = "";

        /**
         * 分享
         *
         * @param url
         */
        @JavascriptInterface
        public void androidShare(String url) {
            Log.e("分享---->>", url);
            String[] split = url.split("&");

            if (split.length > 1) {
                String url1 = split[0];
                String url2 = split[1];
                String img = split[2].substring(split[2].indexOf("=") + 1);
                String title = split[3].substring(split[1].indexOf("=") + 1);
                String desc = split[4].substring(split[3].indexOf("=") + 1);

                Log.e("---img->>", img);

                String userId = (String) SpUtils.get("userId", "");
//
                String substring = url1.substring(url1.indexOf("=") + 1);
                articleId = Integer.parseInt(substring);
                token = url2.substring(url2.indexOf("=") + 1);
                Log.e("----url-->", url1 + "&" + url2 + "&userId=" + userId);
                UMImage image = new UMImage(TencentBrowserActivity.this, img);
                UMWeb web = new UMWeb(url1 + "&" + url2 + "&userId=" + userId);
                web.setTitle(title);//标题
                web.setDescription(desc);//描述
                web.setThumb(image);
                new ShareAction(TencentBrowserActivity.this)
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).open();
            }
        }

        /**
         * 分享回调
         */
        private UMShareListener umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(TencentBrowserActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
                //记录分享
//                1.朋友圈 2.微信好友 3.新浪微博 4.QQ好友
                int types = 0;

                Log.e("platform--->>", platform.getName());
                if (platform.getName().equals("qq")) {
                    types = 4;
                } else if (platform.getName().equals("wxsession")) {
                    types = 2;
                } else if (platform.getName().equals("wxtimeline")) {
                    types = 1;
                } else if (platform.getName().equals("sina")) {
                    types = 3;
                }
                Log.e("-----types-->", types + "");
                RecordShare(articleId, token, types);
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                if (platform.getName().equals("qq")) {
                    if (t.getMessage().indexOf("2008") != -1) {
                        Toast.makeText(TencentBrowserActivity.this, " 分享失败,请先安装QQ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TencentBrowserActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();

                    }
                } else if (platform.getName().equals("weixin") || platform.getName().equals("weixin_circle")) {
                    if (t.getMessage().indexOf("2008") != -1) {
                        Toast.makeText(TencentBrowserActivity.this, " 分享失败,请先安装 微信" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TencentBrowserActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
                    }
                } else if (platform.getName().equals("sina")) {
                    Toast.makeText(TencentBrowserActivity.this, "新浪", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(TencentBrowserActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
                Log.e("platform--22->>", platform.getName());
            }
        };

        private void RecordShare(int articleId, String token, int types) {
            HttpService service = ServiceGenerator.createService(HttpService.class);
            Call<SharefoResult> sharefoResultCall = service.loginShare(articleId, token, types);
            sharefoResultCall.enqueue(new Callback<SharefoResult>() {
                @Override
                public void onResponse(Call<SharefoResult> call, Response<SharefoResult> response) {
                    if (response.isSuccessful()) {
                        SharefoResult body = response.body();
                        if (body.getResult().getCode().equals("200")) {
                            Log.e("--  -->", "分享记录成功");
                        } else {
                            Log.e("--  -->", "分享记录失败");
                        }
                    }
                }

                @Override
                public void onFailure(Call<SharefoResult> call, Throwable t) {
                    Log.e("----->>", t.getMessage());
                }
            });
        }


        /**
         * 返回
         */
        @JavascriptInterface
        public void back() {
            new Thread() {
                public void run() {
                    SystemClock.sleep(10);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (webView.canGoBack()) {
                                webView.goBack();// 返回前一个页面
                            } else {
                                TencentBrowserActivity.this.finish();
                            }
                        }
                    });
                }
            }.start();
        }


        /**
         * 跳转到登录页
         *
         * @param newUrl
         */
        @JavascriptInterface
        public void gotoLogin(String newUrl) {
//            mNewUrl = newUrl;
            Intent intent = new Intent(TencentBrowserActivity.this, UserLogingActivity.class);
            startActivity(intent);
//            TencentBrowserActivity.this.startActivityForResult(intent, RE_LOAD_URL);
        }


        /**
         * 跳转到任何页面（简易传参）
         * <p>
         * 网页端跳转样例
         * <a onclick="baobao.gotoAnyWhere('com.example.loveamall.MovieDetailActivity,iOS.MovieDetailViewController:movieId=(int)123')">gotoAnyWhere</a>
         *
         * @param url
         */
        @JavascriptInterface
        public void gotoAnyWhere(String url) {
            if (url == null)
                return;

            String pageName;//获取要跳转的页面名称
            int posPageName = url.indexOf(",");
            if (posPageName == -1) {
                pageName = url;
            } else {
                pageName = url.substring(0, posPageName);
            }

            if (pageName == null || pageName.trim() == "")
                return;
            Intent intent = new Intent();

            int pos = url.indexOf(":");
            if (pos > 0) {//设置跳转参数
                String strParams = url.substring(pos);
                String[] pairs = strParams.split("&");
                for (String strKeyAndValue : pairs) {
                    String[] arr = strKeyAndValue.split("=");
                    String key = arr[0];
                    String value = arr[1];
                    if (value.startsWith("(int)")) {
                        intent.putExtra(key,
                                Integer.valueOf(value.substring(5)));
                    } else if (value.startsWith("(Double)")) {
                        intent.putExtra(key,
                                Double.valueOf(value.substring(8)));
                    } else {
                        intent.putExtra(key, value);
                    }
                }
            }
            try {//反射设置跳转页面
                intent.setClass(TencentBrowserActivity.this, Class.forName(pageName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}