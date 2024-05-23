package com.yi.homework.model.vo.response.table;

import lombok.Data;

@Data
public class StudentHomeworkItemVO {

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

    private String teacherComment;
}