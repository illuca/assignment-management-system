package com.yi.homework.model.vo.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class ResultVO implements Serializable {

    public static final int NOT_LOGGED_IN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int ROLE_ERROR = 2;

    public static final int PERMISSION_DENIED = 3;

    public static final int INVALID_PARAMETER = 4;

    public static final int INTERNAL_SERVER_ERROR = 5;

    public static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object data;

    public ResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}