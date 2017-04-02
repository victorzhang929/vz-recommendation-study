package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.Log;
import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.UserMapper;
import com.victorzhang.cfs.service.LogService;
import com.victorzhang.cfs.service.UserService;
import com.victorzhang.cfs.util.CommonUtils;
import com.victorzhang.cfs.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.victorzhang.cfs.util.Constants.*;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    @Override
    protected BaseMapper<User, String> getMapper() {
        return userMapper;
    }

    @Override
    public User doLoginByUsernameAndPassword(String username, String password, HttpServletRequest request) throws Exception {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("password", new MD5Utils().getMD5ofStr(password));
            logger.info(username + TRY_LOGIN);

            User user = userMapper.getUserByUsernameAndPassword(map);
            if (user != null) {
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("roleId", user.getRole_id());
                saveLoginOutLog(request, user.getId(), LOGIN_SYSTEM);
                logger.info(username + LOGIN_SUCCESS);
                return user;
            }
            logger.error(user + LOGIN_FAILE);
        }
        return null;
    }

    private void saveLoginOutLog(HttpServletRequest request, String userId, String loginOutMsg) throws Exception {
        if (StringUtils.isNotEmpty(userId)) {
            Log log = new Log();
            log.setId(CommonUtils.newUuid());
            log.setLog_type(loginOutMsg);
            log.setLog_content(request.getHeader("user-agent"));
            log.setUser_id(userId);
            log.setUser_date(CommonUtils.getDateTime());
            log.setUser_ip(CommonUtils.getIpAddr());
            logService.save(log);
        }
    }

}
