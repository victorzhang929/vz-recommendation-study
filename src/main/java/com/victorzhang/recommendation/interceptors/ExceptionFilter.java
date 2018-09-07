package com.victorzhang.recommendation.interceptors;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import static com.victorzhang.recommendation.util.Constants.FILE_NOT_FOUND;

/**
 * Error URL Handler
 * Created by victorzhang on 2017/3/31.
 */
@Component
public class ExceptionFilter implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("ex", ex);

        if (ex instanceof FileNotFoundException) {
            logger.warn(FILE_NOT_FOUND);
            return null;
        } else {
            return new ModelAndView("error", map);
        }
    }
}
