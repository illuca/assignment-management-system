package com.yi.homework.manager.teacher;

import com.yi.homework.dao.HomeworkDAO;
import com.yi.homework.dao.StudentHomeworkDAO;
import com.yi.homework.manager.BaseManager;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.request.AddHomeworkItemVO;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TeacherHomeworkManager extends BaseManager {

    private final HomeworkDAO homeworkDAO;

    private final StudentHomeworkDAO studentHomeworkDAO;

    public TeacherHomeworkManager(HomeworkDAO homeworkDAO, StudentHomeworkDAO studentHomeworkDAO) {
        this.homeworkDAO = homeworkDAO;
        this.studentHomeworkDAO = studentHomeworkDAO;
    }

    public Boolean addHomework(Homework homework) {
        return homeworkDAO.insert(homework) > 0;
    }

    public Boolean addHomeworkByVO(AddHomeworkItemVO addHomeworkItemVO) {
        Homework homework = new Homework();
        homework.setTeacherId(addHomeworkItemVO.getTeacherId());
        homework.setHomeworkTitle(addHomeworkItemVO.getHomeworkTitle());
        homework.setHomeworkContent(addHomeworkItemVO.getHomeworkContent());
        return addHomework(homework);
    }

    public Boolean deleteHomework(Long homeworkId) {
        return homeworkDAO.deleteById(homeworkId) > 0 && studentHomeworkDAO.deleteStudentHomeworkByHomeworkId(homeworkId) > 0;
    }

    public Boolean updateHomework(Homework homework) {
        return homeworkDAO.updateById(homework) > 0;
    }

    public HomeworkItemVO getHomework(Long homeworkId) {
        return homeworkDAO.getHomeworkItemVO(homeworkId);
    }

    public Integer getPageCount(Long teacherId, Long homeworkId, String homeworkTitle) {
        int count = homeworkDAO.countTeacherHomework(teacherId, homeworkId, homeworkTitle);
        return calculatePageNum(count, HomeworkDAO.PAGE_SIZE);
    }

    public List<HomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle) {
        return homeworkDAO.getTeacherHomeworkPage(index, teacherId, homeworkId, homeworkTitle);
    }
}