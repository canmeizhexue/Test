package com.canmeizhexue.test.media;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>LightActivity类 概述，提供XXX功能</p>
 *
 * @author silence
 * @version 1.0 (2016-10-27 10:20)
 */
public class LightActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.open)
    Button open;
    @Bind(R.id.close)
    Button close;
    private Camera.Parameters parameter = null;
    private Camera camera = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.open,R.id.close})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                LogUtil.d("silence","----close----");
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameter);
                camera.release();
                break;
            case R.id.open:
                LogUtil.d("silence","----open----");
                camera = Camera.open();
                camera.startPreview();
                parameter = camera.getParameters();
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameter);
                break;

            default:
                break;
        }
    }
}
