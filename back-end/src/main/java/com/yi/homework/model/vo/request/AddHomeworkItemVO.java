package com.yi.homework.model.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AddHomeworkItemVO {

    private Long teacherId;

    @NotBlank
    private String homeworkTitle;

    @NotBlank
    private String homeworkContent;

}