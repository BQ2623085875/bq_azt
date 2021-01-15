package com.azhyun.massxj.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.azhyun.massxj.R;

import im.delight.android.webview.AdvancedWebView;

public class MyWebViewActivity extends Activity implements AdvancedWebView.Listener {

    private AdvancedWebView mWebView;
    private ImageView mImg;
    private TextView mtitlte;
    private ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        mImg = (ImageView) findViewById(R.id.bank);
        mtitlte = (TextView) findViewById(R.id.title);
        mWebView.setListener(this, this);


        initView();
        // ...
    }

    private void initView() {
        mtitlte.setText("土地托管");
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        SystemClock.sleep(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mWebView.canGoBack()) {
                                    mWebView.goBack();// 返回前一个页面
                                } else {
                                    finish();
                                }
                            }
                        });
                    }
                }.start();
            }
        });
        String url = getIntent().getStringExtra("url");
        initProgressBar();
        mWebView.setWebChromeClient(new WebViewClient() );
        mWebView.loadUrl(url);
    }

    private void initProgressBar() {
        progressBar1.setMax(100);
        progressBar1.setProgressDrawable(this.getResources()
                .getDrawable(R.drawable.color_progressbar));
    }
    private class WebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar1.setProgress(newProgress);
            if(newProgress==100){
                progressBar1.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }

    }
    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        // ...
        super.onBackPressed();
    }



    @Override
    public void onPageStarted(String url, Bitmap favicon) { }

    @Override
    public void onPageFinished(String url) { }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) { }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) { }

    @Override
    public void onExternalPageRequest(String url) { }

}