package com.victorzhang.recommendation.util;


import com.victorzhang.recommendation.domain.User;

public class GenerateLinkUtils {

    private static final String CHECK_CODE = "checkcode";
    private static final String DO_RESET_PASSWORD_URL = "http://localhost:8080/recommendation/index/forwardResetPasswordUI.index?username=";

    public static String generateResetPwdLink(User user) {
        return DO_RESET_PASSWORD_URL + user.getUsername() + "&" + CHECK_CODE + "=" + generateCheckcode(user);
    }

    public static String generateCheckcode(User user) {
        String username = user.getUsername();
        String randomCode = user.getRandomCode();
        return new MD5Utils().getMD5ofStr(username + randomCode);
    }
}
