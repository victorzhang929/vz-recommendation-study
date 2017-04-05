package com.victorzhang.cfs.service.impl;

import com.victorzhang.cfs.domain.User;
import com.victorzhang.cfs.mapper.BaseMapper;
import com.victorzhang.cfs.mapper.UserMapper;
import com.victorzhang.cfs.service.LogService;
import com.victorzhang.cfs.service.UserService;
import com.victorzhang.cfs.util.CommonUtils;
import com.victorzhang.cfs.util.EmailUtils;
import com.victorzhang.cfs.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
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
                request.getSession().setAttribute("roleId", user.getRoleId());
                logService.saveLogByLogTypeAndLogContent(LOGIN_SYSTEM, request.getHeader("user-agent"));
                logger.info(username + LOGIN_SUCCESS);
                return user;
            }
            logger.error(user + LOGIN_FAILE);
        }
        return null;
    }

    @Override
    public String doGetUserByEmail(String email) throws Exception {
        if (StringUtils.isNotEmpty(email)) {
            logger.info(email + TRY_RESET_PASSWORD);
            User user = userMapper.getUserByEmail(email);
            if (user != null) {
                EmailUtils.sendResetPasswordEmail(user);
                logger.info(email + SEND_PASSWORD_URL_TO_EMAIL);
                boolean flag = logService.saveLogByLogTypeAndLogContent(FIND_PASSWORD, email, user.getId());
                if (!flag) {
                    logger.error(LOG_INSERT_ERROR);
                    return null;
                }
                return SEND_EMAIL_MSG;
            }
            logger.warn(email + EMAIL_URL_NOT_FIND);
        }
        return null;
    }

    @Override
    public String doResetPassword(String username, String checkCode, String password, String rePassword) throws Exception {
        User user = userMapper.getUserByUsername(username);
        String result = CHECK_URL;
        String _checkCode = new MD5Utils().getMD5ofStr(username + ":" + user.getRandomCode());

        if (StringUtils.equals(_checkCode, checkCode)) {
            if (StringUtils.equals(password, rePassword)) {
                user.setPassword(new MD5Utils().getMD5ofStr(password));
                user.setGmtModify(CommonUtils.getDateTime());
                boolean flag = super.update(user);
                if (!flag) {
                    throw new SQLException(USER_INFO_UPDATE_ERROR);
                }

                logger.info(username + DO_RESET_PASSWORD_SUCCESS);
                result = DO_RESET_PASSWORD_SUCCESS;
            } else {
                result = CHECK_TWICE_PASSWORD_IS_SAME;
            }
        }
        return result;
    }

}
