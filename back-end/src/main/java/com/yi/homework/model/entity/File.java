package com.yi.homework.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * File表存储上传的文件信息
 */
@Data
@AllArgsConstructor
@ToString
public class File implements Serializable {

    private static final long serialVersionUID = -6956947981866795431L;

    private Long id;
    private String name;
    private String md5;
    private String path;
    private Date uploadTime;
    private String ext; // 文件扩展名

    public File() {
    }

    public File(String name, String md5, String path, Date uploadTime) {
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.uploadTime = uploadTime;
    }

    public File(String name, String md5, String path, Date uploadTime, String ext) {
        this.name = name;
        this.md5 = md5;
        this.path = path;
        this.uploadTime = uploadTime;
        this.ext = ext;
    }
}