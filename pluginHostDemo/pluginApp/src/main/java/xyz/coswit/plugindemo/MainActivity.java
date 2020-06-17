package xyz.coswit.plugindemo;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

public class MainActivity extends Activity {

    Activity proxyActivity;

    public void attach(Activity activity) {
        this.proxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (proxyActivity == null) {
            super.onCreate(savedInstanceState);
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (proxyActivity != null) {
            proxyActivity.setContentView(layoutResID);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if (proxyActivity == null) {
            super.onStart();
        }
    }

    @Override
    public void onPause() {
        if (proxyActivity == null) {
            super.onPause();
        }
    }

    @Override
    public void onResume() {
        if (proxyActivity == null) {
            super.onResume();
        }
    }

    @Override
    public void onStop() {
        if (proxyActivity == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (proxyActivity == null) {
            super.onDestroy();
        }
    }

    @Override
    public Resources.Theme getTheme() {
        return proxyActivity == null ? super.getTheme() : proxyActivity.getTheme();
    }

    @Override
    public Resources getResources() {
        return proxyActivity == null ? super.getResources() : proxyActivity.getResources();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return proxyActivity == null ? super.getLayoutInflater() : proxyActivity.getLayoutInflater();
    }
}
