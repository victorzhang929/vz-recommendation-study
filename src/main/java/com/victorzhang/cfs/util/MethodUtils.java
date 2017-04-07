package com.victorzhang.cfs.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtils {
    public static Object execMethod(Object obj, String methodName, Class<?>[] args, Object[] values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (args == null) {
            Method m = obj.getClass().getMethod(methodName, new Class[0]);

            return m.invoke(obj, new Object[0]);
        }
        Method m = obj.getClass().getMethod(methodName, args);

        return m.invoke(obj, values);
    }
}
