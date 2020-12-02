package com.peter.autochangeicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private long exitTime;

    private String currentString;

    private TextView style1, style2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        style1 = findViewById(R.id.style_tv1);
        style2 = findViewById(R.id.style_tv2);

        style1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentString = "com.peter.autochangeicon.jiqiren";
            }
        });

        style2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentString = "com.peter.autochangeicon.unknown";
            }
        });

        findViewById(R.id.style_tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentString = "com.peter.autochangeicon.MainActivity";
            }
        });
    }


    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            if (!TextUtils.isEmpty(currentString)) {
                changeIcon(currentString);
            }
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }

    public void changeIcon(String activityPath) {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, activityPath),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 0);


        //重启桌面 加速显示
//        restartSystemLauncher(pm);
    }
}