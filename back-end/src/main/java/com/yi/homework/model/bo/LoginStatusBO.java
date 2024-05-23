package com.yi.homework.model.bo;

import lombok.Data;


@Data
public class LoginStatusBO {

    private Boolean loggedIn;

    private Long userId;

    private String username;

    private Integer userType;

    public static LoginStatusBO fromAuthInfo(AuthInfoBO authInfoBO) {
        LoginStatusBO loginStatusBO = new LoginStatusBO();
        loginStatusBO.setLoggedIn(true);
        loginStatusBO.setUserId(authInfoBO.getUserId());
        loginStatusBO.setUsername(authInfoBO.getUsername());
        loginStatusBO.setUserType(authInfoBO.getUserType());

        return loginStatusBO;
    }

}