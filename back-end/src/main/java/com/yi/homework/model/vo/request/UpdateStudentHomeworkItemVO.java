package com.yi.homework.model.vo.request;

import lombok.Data;


@Data
public class UpdateStudentHomeworkItemVO {

    private Long studentHomeworkId;

    private Long studentId;

    private String studentName;

    private Long homeworkId;

    private String homeworkTitle;

    private String homeworkContent;

    private Long teacherId;

    private String teacherName;

    private String title;

    private String content;

}