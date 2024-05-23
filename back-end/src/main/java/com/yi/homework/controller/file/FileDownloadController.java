package com.yi.homework.controller.file;

import com.yi.homework.model.bo.LoginStatusBO;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.*;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/File")
@CrossOrigin
@Api(tags = "文件下载相关接口", description = "提供文件下载相关的 Rest API")
public class FileDownloadController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    //文件下载相关代码
    @ApiOperation("文件下载接口")
    @GetMapping("/download")
    public void downloadFile(@PathParam("studentId") Long studentId, @PathParam("homeworkdId") Long homeworkId, HttpServletRequest request, HttpServletResponse response) {
        // 查登录用户、作业名、作业路径

        StudentHomeworkFileItemVO fileInfo = fileService.getFileInfo(studentId, homeworkId);
        String fileLocation = fileInfo.getFilePath();
        String fileName = fileInfo.getFileName();

        //当前是从工程的WEB-INF//FILE//下获取问价（该目录可以在下面一行代码配置）
        java.io.File file = new java.io.File(fileLocation);
        logger.info("-->" + file.getAbsolutePath());

        if (file.exists()) {
            // 设置强制下载不打开
            // response.setContentType("application/force-download");
            response.setContentType("application/zip");
            //设置文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                logger.info("下载成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } // file exists
    } // 函数结束
}