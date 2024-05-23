package com.yi.homework.model.vo.response.table;

import lombok.Data;

@Data
public class StudentHomeworkFileItemVO {

    private Long studentHomeworkFileId;

    private Long studentHomeworkId;

    private Long studentId;

    private Long fileId;

    private String fileName;

    private String filePath;
}