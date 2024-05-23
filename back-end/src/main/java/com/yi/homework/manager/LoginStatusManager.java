package com.yi.homework.manager;

import com.yi.homework.model.bo.LoginStatusBO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;


@Component
public class LoginStatusManager extends BaseManager {

    private static final String SESSION_USER_STATUS = "user_status";

    public void setLoginStatus(HttpSession httpSession, LoginStatusBO loginStatusBO) {
        httpSession.setAttribute(SESSION_USER_STATUS, loginStatusBO);
    }

    public LoginStatusBO getLoginStatus(HttpSession httpSession) {
        LoginStatusBO loginStatusBO = (LoginStatusBO) httpSession.getAttribute(SESSION_USER_STATUS);

        if (loginStatusBO == null) {
            loginStatusBO = new LoginStatusBO();
            setLoginStatus(httpSession, loginStatusBO);
        }

        return loginStatusBO;
    }

}