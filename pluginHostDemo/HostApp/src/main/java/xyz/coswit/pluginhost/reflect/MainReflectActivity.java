package xyz.coswit.pluginhost.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import xyz.coswit.pluginhost.R;
import xyz.coswit.pluginhost.util.Utils;

public class MainReflectActivity extends Activity {

    private String pluginPath;

    private String apkName = "pluginApp.apk";

    private String activityName = "xyz.coswit.plugindemo.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reflect);

        init();

        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               StubReflectActivity.startActivity(MainReflectActivity.this,pluginPath,activityName);
            }

        });
    }

    private void init() {
        try {
            Utils.extractAssets(this,apkName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        pluginPath = new File(getFilesDir().getAbsolutePath(),apkName).getAbsolutePath();
//        File dexOutPath = new File(getFilesDir(),"dexout");
//        if(!dexOutPath.exists()){
//            dexOutPath.mkdirs();
//        }
    }


}
