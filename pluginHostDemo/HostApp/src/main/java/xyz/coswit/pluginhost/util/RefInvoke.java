package xyz.coswit.pluginhost.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefInvoke {

    //无参
    public static Object createObject(String className) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTypes, pareValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //无参
    public static Object createObject(Class clazz) {
        Class[] pareTyple = new Class[]{};
        Object[] pareValues = new Object[]{};

        return createObject(clazz, pareTyple, pareValues);
    }

    //一个参数
    public static Object createObject(String className, Class pareType, Object pareValue) {
        Class[] pareTypes = new Class[]{ pareType };
        Object[] pareValues = new Object[]{ pareValue };

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTypes, pareValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //一个参数
    public static Object createObject(Class clazz, Class pareTyple, Object pareVaule) {
        Class[] pareTypes = new Class[]{ pareTyple };
        Object[] pareValues = new Object[]{ pareVaule };

        return createObject(clazz, pareTypes, pareValues);
    }

    //多个参数
    public static Object createObject(String className, Class[] pareTypes, Object[] pareValues) {
        try {
            Class r = Class.forName(className);
            return createObject(r, pareTypes, pareValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    //多个参数
    public static Object createObject(Class clazz, Class[] pareTypes, Object[] pareValues) {
        try {
            Constructor ctor = clazz.getDeclaredConstructor(pareTypes);
            ctor.setAccessible(true);
            return ctor.newInstance(pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    //多个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] pareTypes, Object[] pareValues) {
        if (obj == null)
            return null;

        try {
            //调用一个private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, pareTypes); //在指定类中获取指定的方法
            method.setAccessible(true);
            return method.invoke(obj, pareValues);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    //一个参数
    public static Object invokeInstanceMethod(Object obj, String methodName, Class pareTyple, Object pareVaule) {
        Class[] pareTypes = {pareTyple};
        Object[] pareValues = {pareVaule};

        return invokeInstanceMethod(obj, methodName, pareTypes, pareValues);
    }

    //无参
    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};

        return invokeInstanceMethod(obj, methodName, pareTypes, pareValues);
    }




    //无参
    public static Object invokeStaticMethod(String className, String method_name) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};

        return invokeStaticMethod(className, method_name, pareTypes, pareValues);
    }

    //一个参数
    public static Object invokeStaticMethod(String className, String method_name, Class pareTyple, Object pareVaule) {
        Class[] pareTypes = new Class[]{pareTyple};
        Object[] pareValues = new Object[]{pareVaule};

        return invokeStaticMethod(className, method_name, pareTypes, pareValues);
    }

    //多个参数
    public static Object invokeStaticMethod(String className, String method_name, Class[] pareTypes, Object[] pareValues) {
        try {
            Class obj_class = Class.forName(className);
            return invokeStaticMethod(obj_class, method_name, pareTypes, pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //无参
    public static Object invokeStaticMethod(Class clazz, String method_name) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};

        return invokeStaticMethod(clazz, method_name, pareTypes, pareValues);
    }

    //一个参数
    public static Object invokeStaticMethod(Class clazz, String method_name, Class classType, Object pareVaule) {
        Class[] classTypes = new Class[]{classType};
        Object[] pareValues = new Object[]{pareVaule};

        return invokeStaticMethod(clazz, method_name, classTypes, pareValues);
    }

    //多个参数
    public static Object invokeStaticMethod(Class clazz, String method_name, Class[] pareTypes, Object[] pareValues) {
        try {
            Method method = clazz.getDeclaredMethod(method_name, pareTypes);
            method.setAccessible(true);
            return method.invoke(null, pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    //简写版本
    public static Object getFieldObject(Object obj, String filedName) {
        return getFieldObject(obj.getClass(), obj, filedName);
    }

    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldObject(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //简写版本
    public static void setFieldObject(Object obj, String filedName, Object filedValue) {
        setFieldObject(obj.getClass(), obj, filedName, filedValue);
    }

    public static void setFieldObject(Class clazz, Object obj, String filedName, Object filedValue) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldObject(String className, Object obj, String filedName, Object filedValue) {
        try {
            Class obj_class = Class.forName(className);
            setFieldObject(obj_class, obj, filedName, filedValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    public static void setStaticFieldObject(String classname, String filedName, Object filedValue) {
        setFieldObject(classname, null, filedName, filedValue);
    }

    public static void setStaticFieldObject(Class clazz, String filedName, Object filedValue) {
        setFieldObject(clazz, null, filedName, filedValue);
    }
}
