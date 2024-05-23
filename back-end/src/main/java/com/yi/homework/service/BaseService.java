package com.yi.homework.service;

import com.yi.homework.manager.LoginStatusManager;
import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;


public class BaseService {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private LoginStatusManager loginStatusManager;

    private LoginStatusBO getLoginStatus() {
        return loginStatusManager.getLoginStatus(httpSession);
    }

    protected Long getUserId() {
        return getLoginStatus().getUserId();
    }

    protected ResultVO result(Object data) {
        return new ResultVO(ResultVO.SUCCESS, "success", data);
    }

    protected ResultVO result(Object data, String message) {
        return new ResultVO(ResultVO.SUCCESS, message, data);
    }

    protected ResultVO failedResult(String message) {
        return new ResultVO(ResultVO.FAIL, message, null);
    }

    protected ResultVO failedResult(String message, Object data) {
        return new ResultVO(ResultVO.FAIL, message, data);
    }

}