package com.yi.homework.model.vo.response.table;

import lombok.Data;

@Data
public class HomeworkItemVO {

    private Long homeworkId;

    private Long teacherId;

    private String teacherName;

    private String homeworkTitle;

    private String homeworkContent;

}