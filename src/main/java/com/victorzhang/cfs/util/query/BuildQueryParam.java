package com.victorzhang.cfs.util.query;

import com.victorzhang.cfs.util.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class BuildQueryParam {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";
    private static final String GET = "get";

    public static void buildParam(Object entity, GenericQueryParam param) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field[] fields = entity.getClass().getDeclaredFields();
        Field[] arraOfField;

        int len = (arraOfField = fields).length;

        for (int i = 0; i < len; i++) {
            Field field = arraOfField[i];
            String name = field.getName();
            if (!name.equals(SERIAL_VERSION_UID)) {
                String tempName = name.substring(0, 1).toUpperCase() + name.substring(1);
                Object value = MethodUtils.execMethod(entity, GET + tempName, null, null);
                if (value != null) {
                    param.fill(name, value);
                }
            }
        }
    }
}
