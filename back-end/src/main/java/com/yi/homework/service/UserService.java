package com.yi.homework.service;

import com.yi.homework.manager.LoginStatusManager;
import com.yi.homework.manager.UserManager;
import com.yi.homework.model.bo.AuthInfoBO;
import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.vo.response.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserService extends BaseService {

    private final HttpSession httpSession;

    private final UserManager userManager;

    private final LoginStatusManager loginStatusManager;

    public UserService(HttpSession httpSession, UserManager userManager, LoginStatusManager loginStatusManager) {
        this.httpSession = httpSession;
        this.userManager = userManager;
        this.loginStatusManager = loginStatusManager;
    }

    public ResultVO login(String userId, String password, Integer userType) {
        AuthInfoBO authInfoBO = userManager.getAuthInfoByLoginId(userId, userType);

        if (authInfoBO == null) {
            return failedResult("用户不存在");
        }

        // 明文密码加密为md5密码,与数据库存储的md5密码做对比
        String encryptedPassword = userManager.passwordMd5(password);
        if (!encryptedPassword.equals(authInfoBO.getPassword())) {
            return failedResult("密码错误");
        }
        // 生成登录状态BO
        LoginStatusBO loginStatusBO = LoginStatusBO.fromAuthInfo(authInfoBO);
        loginStatusManager.setLoginStatus(httpSession, loginStatusBO);
        // TODO测试,要删除

        Long userId1 = getUserId();

        return result(loginStatusBO);
    }

    public ResultVO register(String userId, String username, String password, Integer userType) {
        if (!userManager.checkIsUserIdValid(userId, userType)) {
            return failedResult("该账号已注册");
        }

        if (!userManager.register(userId, username, password, userType)) {
            return failedResult("注册时发生未知错误");
        } else {
            return result("注册成功", username);
        }
    }

    public ResultVO logout() {
        loginStatusManager.setLoginStatus(httpSession, null);
        return result("注销成功");
    }

    public ResultVO getLoginStatus() {
        return result(loginStatusManager.getLoginStatus(httpSession));
    }
}