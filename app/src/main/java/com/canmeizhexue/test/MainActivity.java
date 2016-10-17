package com.canmeizhexue.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bugly初始化,,当然也可以通过AndroidManifest文件来配置，详细情况可以查看bugly的高级配置文档
        CrashReport.initCrashReport(this,"0acbfe93a1", BuildConfig.DEBUG);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TestClass().doTest();
            }
        });
        ImageView imageView= (ImageView) findViewById(R.id.image);
//        ImageLoaderHelper.showImgFromResId(this,imageView,R.mipmap.res_test);
//        ImageLoaderHelper.showImgFromNetwork(this,imageView,"http://www.274745.cc/imgall/obuwgnjonzuxa2ldfzrw63i/20100121/1396946_104643942888_2.jpg");
        ImageLoaderHelper.showImgFromUri(this,imageView,ImageLoaderHelper.resourceIdToUri(this,R.mipmap.ic_launcher));

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void test(){

    }


}
