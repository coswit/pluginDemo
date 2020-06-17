package xyz.coswit.pluginhost.reflect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * @author Created by zhengjing on 2020/6/11.
 */
public class StubReflectActivity extends StubBaseActivity {

    private ReflectActivity reflectActivity;

    public static void startActivity(Context preCtx, String pluginPath, String activityName) {
        Intent intent = new Intent(preCtx, StubReflectActivity.class);
        intent.putExtra(StubBaseActivity.PLUGIN_PATH, pluginPath);
        intent.putExtra(StubBaseActivity.ACTIVITY_NAME,activityName);
        preCtx.startActivity(intent);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reflectActivity = new ReflectActivity(classLoader, activityName);
        reflectActivity.attach(this);
        reflectActivity.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reflectActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reflectActivity.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        reflectActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        reflectActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reflectActivity.onDestroy();
    }
}
