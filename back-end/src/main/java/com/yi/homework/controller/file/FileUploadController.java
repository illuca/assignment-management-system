package com.yi.homework.controller.file;

import com.yi.homework.service.UserService;
import com.yi.homework.service.file.FileService;
import com.yi.homework.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/File")
@CrossOrigin
@Api(tags = "文件上传相关接口", description = "提供文件上传相关的 Rest API")
public class FileUploadController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @ApiOperation("文件上传接口")
    @PostMapping("/")
    public void upload(String name,
                       String md5,
                       MultipartFile file, Long homeworkId, Long userId) throws IOException {

        if (fileService.upload(name, md5, file, homeworkId, userId) == true) {
            logger.info("文件上传成功");
        } else {
            logger.info("文件上传失败");
        }
    }
}