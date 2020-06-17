package xyz.coswit.pluginhost.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * @author Created by zhengjing on 2020/6/11.
 */
public class ReflectActivity {

    private Activity activity;
    private  Class<Activity> clazz;


    public ReflectActivity(ClassLoader classLoader, String activityName) {
        try {
            clazz = (Class<Activity>) classLoader.loadClass(activityName);
            activity = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void attach(Activity proxyActivity){
        try {
            clazz.getMethod("attach",Activity.class).invoke(activity,proxyActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            clazz.getMethod("onCreate",Bundle.class).invoke(activity,savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        try {
            clazz.getMethod("onStart").invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        try {
            clazz.getMethod("onResume").invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void onPause() {
        try {
            clazz.getMethod("onPause").invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        try {
            clazz.getMethod("onStop").invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        try {
            clazz.getMethod("onDestroy").invoke(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
