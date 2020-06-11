package xyz.coswit.pluginhost.hooker.ams;

import android.content.pm.PackageInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


class MockClass3 implements InvocationHandler {

    private Object base;

    public MockClass3(Object base) {
        this.base = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getPackageInfo")) {
            return new PackageInfo();
        }
        return method.invoke(base, args);
    }
}
