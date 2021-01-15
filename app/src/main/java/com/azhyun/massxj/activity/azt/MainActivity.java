package com.azhyun.massxj.activity.azt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.azhyun.massxj.R;

public class MainActivity extends AppCompatActivity {

    private WebView viewById;
    private TextView name;
    private TextView lai;
    private TextView shi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (WebView) findViewById(R.id.ww);
        name = (TextView) findViewById(R.id.name);
        lai = (TextView) findViewById(R.id.laiyuan);
        shi = (TextView) findViewById(R.id.shijian);


        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");



        WebSettings webSettings = this.viewById.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);//重点是这个设置
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//对H5支持
        this.viewById.loadUrl(url);





    }
}
