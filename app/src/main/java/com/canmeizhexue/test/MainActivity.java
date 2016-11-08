package com.canmeizhexue.test;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.base.BaseAdapter;
import com.canmeizhexue.test.facade.QQMessageTriggerActivity;
import com.canmeizhexue.test.image.CoordinateActivity;
import com.canmeizhexue.test.image.ImageScaleTypeActivity;
import com.canmeizhexue.test.image.ImageViewActivity;
import com.canmeizhexue.test.image.MatrixDemoActivity;
import com.canmeizhexue.test.image.TouchEventActivity;
import com.canmeizhexue.test.media.LightActivity;
import com.canmeizhexue.test.recyclerview.RecyclerViewActivity;
import com.canmeizhexue.test.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity  implements AdapterView.OnItemClickListener{

    @Bind(R.id.lv_demo)
    ListView lvDemo;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;

    private List<DemoModel> demoModels;
    private DemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        demoModels = new ArrayList<>();
        initData();
        demoAdapter = new DemoAdapter(this,demoModels);
        lvDemo.setAdapter(demoAdapter);
        lvDemo.setOnItemClickListener(this);
//        printClassLoader();

    }

    @Override
    protected void onResume() {
        super.onResume();
/*        // TODO 测试BlockCanary
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DemoModel currdemo = (DemoModel) adapterView.getItemAtPosition(i);
        if (currdemo!=null && currdemo.clazz != null) {
            startActivity(new Intent(this, currdemo.clazz));
        }

    }
    private void initData(){

        demoModels.clear();
        demoModels.add(new DemoModel("ImageScaleTypeActivity", ImageScaleTypeActivity.class));
        demoModels.add(new DemoModel("ImageViewActivity", ImageViewActivity.class));
        demoModels.add(new DemoModel("MatrixDemoActivity", MatrixDemoActivity.class));
        demoModels.add(new DemoModel("CoordinateActivity", CoordinateActivity.class));
        demoModels.add(new DemoModel("TouchEventActivity", TouchEventActivity.class));
        demoModels.add(new DemoModel("LightActivity", LightActivity.class));
        demoModels.add(new DemoModel("QQMessageTriggerActivity", QQMessageTriggerActivity.class));
        demoModels.add(new DemoModel("RecyclerViewActivity", RecyclerViewActivity.class));
    }
    private void printClassLoader(){
        Application application = getApplication();

        LogUtil.d("silence","----getPackageCodePath---"+application.getPackageCodePath());
        LogUtil.d("silence","---getPackageResourcePath----"+application.getPackageResourcePath());
        LogUtil.d("silence","---getCacheDir----"+application.getCacheDir());
        LogUtil.d("silence","---getExternalCacheDir----"+application.getExternalCacheDir());
        LogUtil.d("silence","---getFilesDir----"+application.getFilesDir());
        LogUtil.d("silence","---getObbDir----"+application.getObbDir());
        //这些目录在应用程序卸载的时候会删除
        LogUtil.d("silence","---getExternalFilesDir----"+application.getExternalFilesDir(null));
        LogUtil.d("silence","---getExternalFilesDir----"+application.getExternalFilesDir("hello"));

        ApplicationInfo applicationInfo=application.getApplicationInfo();
        LogUtil.d("silence","---dataDir----"+applicationInfo.dataDir);
        LogUtil.d("silence","---sourceDir----"+applicationInfo.sourceDir);
//        LogUtil.d("silence","---deviceProtectedDataDir----"+applicationInfo.deviceProtectedDataDir);
        LogUtil.d("silence","---nativeLibraryDir----"+applicationInfo.nativeLibraryDir);
        LogUtil.d("silence","---publicSourceDir----"+applicationInfo.publicSourceDir);
        LogUtil.d("silence","---sharedLibraryFiles----"+applicationInfo.sharedLibraryFiles);
        LogUtil.d("silence","---sourceDir----"+applicationInfo);

//        LogUtil.d("silence","---getObbDir----"+application.getObbDirs());
//        LogUtil.d("silence","---getCodeCacheDir----"+application.getCodeCacheDir());


        Log.i("DEMO", "Context的类加载加载器:"+Context.class.getClassLoader());
        Log.i("DEMO", "ListView的类加载器:"+ListView.class.getClassLoader());
        Log.i("DEMO", "应用程序默认加载器:"+getClassLoader());
        Log.i("DEMO", "系统类加载器:"+ClassLoader.getSystemClassLoader());
        Log.i("DEMO", "系统类加载器和Context的类加载器是否相等:"+(Context.class.getClassLoader()==ClassLoader.getSystemClassLoader()));
        Log.i("DEMO", "系统类加载器和应用程序默认加载器是否相等:"+(getClassLoader()==ClassLoader.getSystemClassLoader()));

        Log.i("DEMO","打印应用程序默认加载器的委派机制:");
        ClassLoader classLoader = getClassLoader();
        while(classLoader != null){
            Log.i("DEMO", "类加载器:"+classLoader);
            classLoader = classLoader.getParent();
        }

        Log.i("DEMO","打印系统加载器的委派机制:");
        classLoader = ClassLoader.getSystemClassLoader();
        while(classLoader != null){
            Log.i("DEMO", "类加载器:"+classLoader);
            classLoader = classLoader.getParent();
        }
    }

    /**
     * <p>DemoAdapter类 </p>
     *
     * @author silence
     * @version 1.0 (2015/10/19)
     */
    public class DemoAdapter extends BaseAdapter<DemoModel> {
        /**
         * 构造函数，在这里初始化各项属性值
         *
         * @param context  上下文
         * @param list     数据列表
         */
        public DemoAdapter(Context context, List<DemoModel> list) {
            super(context, list);
        }

        /**
         * 构造函数，在这里初始化各项属性值
         *
         * @param context  上下文
         */
        public DemoAdapter(Context context) {
            super(context);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.item_demo, parent, false);
            }
            DemoModel currDemo = list.get(position);

            TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvName.setText(currDemo.name);

            return convertView;
        }
    }
    public static class DemoModel{
        /**
         * 名字
         */
        public String name;
        /**
         * 跳转界面的action
         */
        public Class clazz;

        /**
         * 构造方法
         *
         * @param name  名字
         * @param clazz 跳转的Activity的class
         */
        public DemoModel(String name, Class clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }


}
