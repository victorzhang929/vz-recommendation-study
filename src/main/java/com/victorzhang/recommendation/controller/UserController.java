package com.victorzhang.recommendation.controller;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.victorzhang.recommendation.domain.User;
import com.victorzhang.recommendation.service.UserService;
import com.victorzhang.recommendation.util.CommonUtils;
import com.victorzhang.recommendation.util.query.GenericQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.victorzhang.recommendation.util.Constants.EMPTY_STRING;
import static com.victorzhang.recommendation.util.Constants.PLEASE_INPUT_RIGHT_EMAIL;
import static com.victorzhang.recommendation.util.Constants.PLEASE_INPUT_RIGHT_ID_CARD;
import static com.victorzhang.recommendation.util.Constants.PLEASE_INPUT_RIGHT_PHONE_NUMBER;
import static com.victorzhang.recommendation.util.Constants.REAL_NAME_CAN_NOT_EMPTY;
import static com.victorzhang.recommendation.util.Constants.USERNAME_CAN_NOT_EMPTY;
import static com.victorzhang.recommendation.util.Constants.USER_ID;
import static com.victorzhang.recommendation.util.Constants.USER_INFO_UPDATE_ERROR;
import static com.victorzhang.recommendation.util.Constants.USER_INFO_UPDATE_SUCCESS;
import static com.victorzhang.recommendation.util.Constants.USER_TYPE;

@Controller
@RequestMapping("user")
public class UserController {

    private static final String ROLE_NAME = "rolename";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/forwardUserInfoUI")
    public String forwardUserInfoUI() {
        return "userInfo";
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String, Object> getUserInfo() throws Exception {
        return userService.getByUserInfo(CommonUtils.sesAttr(request, USER_ID));
    }

    @RequestMapping(value = "/saveUserInfo.do", produces = {"text/javascript;charset=UTF-8"})
    @ResponseBody
    public String saveUserInfo(String username, String realname, String userMobile, String userIdCard, String userEmail, String gender, String tag) throws Exception {
        Pattern pMobile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern pIdCard = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        Pattern pEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

        if (StringUtils.isEmpty(username)) {
            return USERNAME_CAN_NOT_EMPTY;
        }
        if (StringUtils.isEmpty(realname)) {
            return REAL_NAME_CAN_NOT_EMPTY;
        }
        if (StringUtils.isNotEmpty(userMobile) && !pMobile.matcher(userMobile).matches()) {
            return PLEASE_INPUT_RIGHT_PHONE_NUMBER;
        }
        if (StringUtils.isNotEmpty(userIdCard) && !pIdCard.matcher(userIdCard).matches()) {
            return PLEASE_INPUT_RIGHT_ID_CARD;
        }
        if (StringUtils.isNotEmpty(userEmail) && !pEmail.matcher(userEmail).matches()) {
            return PLEASE_INPUT_RIGHT_EMAIL;
        }
        User user = new User(CommonUtils.sesAttr(request, USER_ID), username, realname, userMobile, userIdCard, userEmail, gender, tag, CommonUtils.getDateTime());
        if(!userService.update(user)){
            throw new SQLException(USER_INFO_UPDATE_ERROR);
        }
        return USER_INFO_UPDATE_SUCCESS;
    }

    @RequestMapping("listPaging.do")
    @ResponseBody
    public Map<String, Object> listPaging(String _page, String _pageSize, String username,String rolename, String startDate, String endDate) throws Exception {
        User user = new User();
        user.setUsername(username);
        if (StringUtils.equals(USER_TYPE, rolename)) {
            rolename = EMPTY_STRING;
        }
        GenericQueryParam param = new GenericQueryParam();
        param.fill(ROLE_NAME, rolename);
        return userService.listPaging(user, _page, _pageSize, startDate, endDate, param);
    }

}
