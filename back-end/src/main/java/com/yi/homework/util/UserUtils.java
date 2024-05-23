package com.yi.homework.util;

import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.vo.response.ResultVO;

import javax.servlet.http.HttpSession;

public class UserUtils {
    public static Long getUserId(ResultVO loginStatus) {
        LoginStatusBO loginStatusBO = (LoginStatusBO) loginStatus.getData();
        Long userId = loginStatusBO.getUserId();
        return userId;
    }
}