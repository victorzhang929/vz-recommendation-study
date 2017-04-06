package com.victorzhang.cfs.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtils {
    public static Object execMethod(Object obj, String methodName, Class<?>[] args,Object[] values) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if(args == null){
            Method method = obj.getClass().getMethod(methodName, new Class[0]);
            return method.invoke(obj, new Object[0]);
        }
        Method method = obj.getClass().getMethod(methodName, args);
        return method.invoke(obj, values);
    }
}
