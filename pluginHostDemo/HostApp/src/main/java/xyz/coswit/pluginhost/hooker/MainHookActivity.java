package xyz.coswit.pluginhost.hooker;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import xyz.coswit.pluginhost.R;
import xyz.coswit.pluginhost.hooker.ams.AMSHookHelper;
import xyz.coswit.pluginhost.hooker.classloader.LoadedApkClassLoaderHookHelper;
import xyz.coswit.pluginhost.util.Utils;

public class MainHookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hook);

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    Intent t = new Intent();
                    t.setComponent(
                            new ComponentName("xyz.coswit.plugindemo",
                                    "xyz.coswit.plugindemo.MainActivity"));

                    startActivity(t);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            Utils.extractAssets(newBase, "pluginApp.apk");

            LoadedApkClassLoaderHookHelper.hookLoadedApkInActivityThread(
                    getFileStreamPath("pluginApp.apk"));
            AMSHookHelper.hookAMN();
            AMSHookHelper.hookActivityThread();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}


