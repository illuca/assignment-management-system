package com.yi.homework.manager.teacher;

import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherSubmittedManager extends BaseManager {

    private final StudentHomeworkDAO studentHomeworkDAO;

    public TeacherSubmittedManager(StudentHomeworkDAO studentHomeworkDAO) {
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    public Boolean updateStudentHomework(StudentHomework studentHomework) {
        return studentHomeworkDAO.updateById(studentHomework) > 0;
    }

    public StudentHomeworkItemVO getStudentHomework(Long studentHomeworkId) {
        StudentHomework studentHomework = studentHomeworkDAO.selectById(studentHomeworkId);
        return studentHomeworkDAO.getStudentHomeworkVO(studentHomework.getStudentId(), studentHomework.getHomeworkId());
    }

    public Integer getPageCount(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        int count = studentHomeworkDAO.count(teacherId, homeworkId, homeworkTitle, studentId, studentName);
        return calculatePageNum(count, StudentHomeworkDAO.PAGE_SIZE);
    }

    public List<StudentHomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkDAO.getPage(index, teacherId, homeworkId, homeworkTitle, studentId, studentName);
    }
}