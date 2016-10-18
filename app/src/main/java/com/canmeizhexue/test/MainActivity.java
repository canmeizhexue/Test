package com.canmeizhexue.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.base.BaseAdapter;
import com.canmeizhexue.test.image.ImageScaleTypeActivity;

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
