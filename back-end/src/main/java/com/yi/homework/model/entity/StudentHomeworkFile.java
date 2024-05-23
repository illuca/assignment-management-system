package com.yi.homework.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@TableName("student_homework_file")
@Data
public class StudentHomeworkFile {

    private static final String STUDENT_HOMEWORK_FILE_ID = "student_homework_file_id";
    private static final String STUDENT_ID = "student_id";
    private static final String HOMEWORK_ID = "homework_id";
    private static final String FILE_ID = "file_id";
    private static final String FILE_NAME = "file_name";
    private static final String FILE_PATH = "file_path";

    /**
     * 学生作业文件编号
     */
    @NotNull
    @TableId(value = STUDENT_HOMEWORK_FILE_ID, type = IdType.AUTO)
    private Long studentHomeworkFileId;

    public StudentHomeworkFile() {
    }
    public StudentHomeworkFile(Long studentId, Long homeworkId, Long fileId, String fileName, String filePath) {
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
    }
    /**
     * 学生学号
     */
    @NotNull
    @TableField(STUDENT_ID)
    private Long studentId;

    /**
     * 作业编号
     */
    @NotNull
    @TableField(HOMEWORK_ID)
    private Long homeworkId;

    @NotNull
    @TableField(FILE_ID)
    private Long fileId;

    @NotNull
    @TableField(FILE_NAME)
    private String fileName;

    @NotNull
    @TableField(FILE_PATH)
    private String filePath;


}