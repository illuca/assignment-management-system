package com.yi.homework.service.file;

import com.yi.homework.config.UploadConfig;
import com.yi.homework.controller.file.FileDownloadController;
import com.yi.homework.dao.mapper.FileMapper;
import com.yi.homework.dao.StudentHomeworkFileDAO;
import com.yi.homework.dao.mapper.StudentHomeworkFileMapper;
import com.yi.homework.dao.mapper.StudentHomeworkMapper;
import com.yi.homework.model.entity.File;
import com.yi.homework.model.entity.StudentHomeworkFile;
import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
import com.yi.homework.service.BaseService;
import com.yi.homework.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static com.yi.homework.util.FileUtils.generateFileName;
import static com.yi.homework.util.UploadUtils.*;

/**
 * 文件上传服务
 */
@Service
public class FileService extends BaseService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private StudentHomeworkFileDAO studentHomeworkFileDAO;

    @Autowired
    private StudentHomeworkFileMapper studentHomeworkFileMapper;

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    /**
     * 上传文件
     *
     * @param md5
     * @param file
     */
    public boolean upload(String name,
                          String md5,
                          MultipartFile file, Long homeworkId, Long userId) throws IOException {
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        File newFile = new File(name, md5, path, new Date(), FileUtils.getExt(file));

        StudentHomeworkFileItemVO fileInfo = studentHomeworkFileMapper.getFileInfo(userId, homeworkId);
        // 查看文件是否已上传
        if (fileInfo == null ) {
            if (fileMapper.save(newFile) > 0) {
                File addedFile = fileMapper.getByFile(newFile);
                StudentHomeworkFile studentHomeworkFile = new StudentHomeworkFile(userId, homeworkId, addedFile.getId(), addedFile.getName(), addedFile.getPath());
                // 用studentHomeworkFileMapper也行
                if (studentHomeworkFileDAO.save(studentHomeworkFile) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public StudentHomeworkFileItemVO getFileInfo(Long studentId, Long homeworkId) {
        return studentHomeworkFileMapper.getFileInfo(studentId, homeworkId);
    }

    /**
     * 分块上传文件
     *
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        addChunk(md5, chunk);
        if (isUploaded(md5)) {
            removeKey(md5);
            fileMapper.save(new File(name, md5, UploadConfig.path + fileName, new Date(), FileUtils.getExt(file)));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     * true:  未上传
     * false: 已上传
     *
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileMapper.getByFile(file) == null;
    }

    public void download(String fileName, String fileLocation, HttpServletRequest request, HttpServletResponse response) {
        //当前是从工程的WEB-INF//FILE//下获取问价（该目录可以在下面一行代码配置）
        java.io.File file = new java.io.File(fileLocation);
        logger.info("-->" + file.getAbsolutePath());

        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
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
        }
    }

    // public StudentHomeworkFileItemVO getFileInfo(Long userId, Long homeworkId) {
    //     return studentHomeworkFileDAO.getFileInfo(userId, homeworkId);
    // }
}