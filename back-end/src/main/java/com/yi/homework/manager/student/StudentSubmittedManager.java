package com.yi.homework.manager.student;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.request.UpdateStudentHomeworkItemVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StudentSubmittedManager extends BaseManager {

    private final StudentHomeworkDAO studentHomeworkDAO;

    public StudentSubmittedManager(StudentHomeworkDAO studentHomeworkDAO) {
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    public Boolean updateStudentHomework(StudentHomework studentHomework) {
        StudentHomework originalStudentHomework = studentHomeworkDAO.selectById(studentHomework.getStudentHomeworkId());

        if (originalStudentHomework.getTeacherComment() != null) {
            return false;
        }

        return studentHomeworkDAO.updateById(studentHomework) > 0;
    }

    public Boolean updateStudentHomeworkByVO(UpdateStudentHomeworkItemVO updateStudentHomeworkItemVO) {
        StudentHomework updatedStudentHomework = new StudentHomework();
        updatedStudentHomework.setStudentHomeworkId(updateStudentHomeworkItemVO.getStudentHomeworkId());
        updatedStudentHomework.setStudentId(updateStudentHomeworkItemVO.getStudentId());
        updatedStudentHomework.setHomeworkId(updateStudentHomeworkItemVO.getHomeworkId());
        updatedStudentHomework.setTitle(updateStudentHomeworkItemVO.getTitle());
        updatedStudentHomework.setContent(updateStudentHomeworkItemVO.getContent());

        return updateStudentHomework(updatedStudentHomework);
    }

    public StudentHomeworkItemVO getStudentHomework(Long studentId, Long homeworkId) {
        return studentHomeworkDAO.getStudentHomeworkVO(studentId, homeworkId);
    }

    public Integer getPageCount(Long homeworkId, String homeworkTitle, Long studentId) {
        int count = studentHomeworkDAO.countWithoutTeacherComment(homeworkId, homeworkTitle, studentId, null);
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    public List<StudentHomeworkItemVO> getPage(Integer index, Long homeworkId, String homeworkTitle, Long studentId) {
        return studentHomeworkDAO.getPageWithoutTeacherComment(index, homeworkId, homeworkTitle, studentId, null);
    }

}