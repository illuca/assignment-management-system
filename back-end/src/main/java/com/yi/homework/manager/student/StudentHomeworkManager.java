package com.yi.homework.manager.student;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.dao.StudentHomeworkFileDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.entity.StudentHomeworkFile;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StudentHomeworkManager extends BaseManager {

    private final HomeworkDAO homeworkDAO;

    private final StudentHomeworkFileDAO studentHomeworkFileDAO;

    private final StudentHomeworkDAO studentHomeworkDAO;

    public StudentHomeworkManager(HomeworkDAO homeworkDAO, StudentHomeworkFileDAO studentHomeworkFileDAO, StudentHomeworkDAO studentHomeworkDAO) {
        this.homeworkDAO = homeworkDAO;
        this.studentHomeworkFileDAO = studentHomeworkFileDAO;
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    public Boolean submitStudentHomework(StudentHomeworkItemVO studentHomeworkItemVO) {
        StudentHomework studentHomework = new StudentHomework();
        studentHomework.setStudentId(studentHomeworkItemVO.getStudentId());
        studentHomework.setHomeworkId(studentHomeworkItemVO.getHomeworkId());
        studentHomework.setTitle(studentHomeworkItemVO.getTitle());
        studentHomework.setContent(studentHomeworkItemVO.getContent());

        return studentHomeworkDAO.insert(studentHomework) > 0;
    }

    public Boolean submitStudentHomeworkFile(StudentHomeworkItemVO studentHomeworkItemVO) {
        Long studentId = studentHomeworkItemVO.getStudentId();
        Long homeworkId = studentHomeworkItemVO.getHomeworkId();
        // 设置studentHomeworkFile
        StudentHomeworkFile studentHomeworkFile = new StudentHomeworkFile();
        studentHomeworkFile.setStudentId(studentHomeworkItemVO.getStudentId());
        studentHomeworkFile.setHomeworkId(studentHomeworkItemVO.getHomeworkId());

        // 根据 student_homework
        StudentHomeworkFileItemVO fileInfo = studentHomeworkFileDAO.getFileInfo(studentId, homeworkId);
        studentHomeworkFile.setFileId(fileInfo.getFileId());
        studentHomeworkFile.setFilePath(fileInfo.getFilePath());

        return null;
    }

    public HomeworkItemVO getHomework(Long homeworkId) {
        return homeworkDAO.getHomeworkItemVO(homeworkId);
    }

    public Integer getPageCount(Long studentId, Long homeworkId, String homeworkTitle) {
        int count = homeworkDAO.countStudentRestHomework(studentId, homeworkId, homeworkTitle);
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    public List<HomeworkItemVO> getPage(Integer index, Long studentId, Long homeworkId, String homeworkTitle) {
        return homeworkDAO.getStudentRestHomeworkPage(index, studentId, homeworkId, homeworkTitle);
    }
}