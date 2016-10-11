package com.canmeizhexue.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
                byte[] bytes = new byte[1024*1024*1024];
                new TestClass().doTest();
            }
        });
    }
    public void test(){

    }

}
