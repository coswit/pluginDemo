package xyz.coswit.pluginhost;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Method;

import xyz.coswit.pluginlibrary.IBean;
import xyz.coswit.pluginlibrary.ICallback;
import xyz.coswit.pluginlibrary.IDynamic;

public class MainActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        final TextView tv = findViewById(R.id.tv);

        //普通调用，反射的方式
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Class mLoadClassBean;
                try {
                    mLoadClassBean = classLoader.loadClass("xyz.coswit.plugindemo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();

                    Method getNameMethod = mLoadClassBean.getMethod("getName");
                    getNameMethod.setAccessible(true);
                    String name = (String) getNameMethod.invoke(beanObject);
                    tv.setText(name);


                } catch (Exception e) {
                    Log.e("DEMO", "msg:" + e.getMessage());
                }
            }
        });


        //带参数调用
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    Class mLoadClassBean = classLoader.loadClass("xyz.coswit.plugindemo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();

                    IBean bean = (IBean) beanObject;
                    bean.setName("Hello");
                    tv.setText(bean.getName());
                } catch (Exception e) {
                    Log.e("DEMO", "msg:" + e.getMessage());
                }

            }
        });

        //带回调函数的调用
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    Class mLoadClassBean = classLoader.loadClass("xyz.coswit.plugindemo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();

                    IBean bean = (IBean) beanObject;


                    ICallback callback = new ICallback() {
                        @Override
                        public void sendResult(String result) {
                            tv.setText(result);
                        }
                    };
                    bean.register(callback);

                    bean.setName("callback");
                } catch (Exception e) {
                    Log.e("DEMO", "msg:" + e.getMessage());
                }

            }
        });


        //带资源文件的调用
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                loadResources();
                Class mLoadClassDynamic = null;

                try {
                    mLoadClassDynamic = classLoader.loadClass("xyz.coswit.plugindemo.Dynamic");
                    Object dynamicObject = mLoadClassDynamic.newInstance();

                    IDynamic dynamic = (IDynamic) dynamicObject;
                    String content = dynamic.getStringForResId(MainActivity.this);
                    tv.setText(content);
                } catch (Exception e) {
                    Log.e("DEMO", "msg:" + e.getMessage());
                }
            }
        });
    }



}
