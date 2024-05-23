package com.yi.homework.model.vo.response;

import lombok.Data;


@Data
public class ParameterErrorVO {

    private String fieldName;

    private String message;

    public ParameterErrorVO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

}