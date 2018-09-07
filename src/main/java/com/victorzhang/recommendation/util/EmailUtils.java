package com.victorzhang.recommendation.util;


import com.victorzhang.recommendation.domain.User;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {

    private static final String FROM = "15822452355@163.com";
    private static final String SUBJECT = "找回您的账户和密码";

    public static void sendResetPasswordEmail(User user) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject(SUBJECT);
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getUserEmail()));
            message.setContent("要使用新的密码, 请使用以下链接启用密码:<br/><a href='" + GenerateLinkUtils.generateResetPwdLink(user) + "'>点击重新设置密码</a>", "text/html;charset=utf-8");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String password = null;
                InputStream is = EmailUtils.class.getResourceAsStream("/password/password.dat");
                byte[] b = new byte[1024];

                try {
                    int len = is.read(b);
                    password = new String(b, 0, len);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new PasswordAuthentication(FROM, password);
            }

        });
        return session;
    }
}
