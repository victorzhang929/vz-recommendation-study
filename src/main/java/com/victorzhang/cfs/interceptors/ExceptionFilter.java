package com.victorzhang.cfs.interceptors;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Error URL Handler
 * Created by victorzhang on 2017/3/31.
 */
public class ExceptionFilter implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("ex", ex);

        if (ex instanceof FileNotFoundException) {
            return null;
        } else {
            return new ModelAndView("error/index", map);
        }
    }
}
