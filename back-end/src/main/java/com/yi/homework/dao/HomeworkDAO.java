package com.yi.homework.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.dao.mapper.HomeworkMapper;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HomeworkDAO extends BaseDAO {

    public static final int PAGE_SIZE = 7;

    private final HomeworkMapper homeworkMapper;

    public HomeworkDAO(HomeworkMapper homeworkMapper) {
        this.homeworkMapper = homeworkMapper;
    }

    public int insert(Homework homework) {
        return homeworkMapper.insert(homework);
    }

    public int deleteById(Long homeworkId) {
        return homeworkMapper.deleteById(homeworkId);
    }

    public int updateById(Homework homework) {
        return homeworkMapper.updateById(homework);
    }

    public Homework selectById(Long homeworkId) {
        return homeworkMapper.selectById(homeworkId);
    }

    public int count(Long homeworkId, String homeworkTitle) {
        return homeworkMapper.count(homeworkId, homeworkTitle);
    }

    public List<HomeworkItemVO> getPage(Integer index, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getPage(page, homeworkId, homeworkTitle).getRecords();
    }

    public int countStudentRestHomework(Long studentId, Long homeworkId, String homeworkTitle) {
        return homeworkMapper.countStudentRestHomework(studentId, homeworkId, homeworkTitle);
    }

    public List<HomeworkItemVO> getStudentRestHomeworkPage(Integer index, Long studentId, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getStudentRestHomeworkPage(page, studentId, homeworkId, homeworkTitle).getRecords();
    }

    public int countTeacherHomework(Long teacherId, Long homeworkId, String homeworkTitle) {
        return homeworkMapper.countTeacherHomework(teacherId, homeworkId, homeworkTitle);
    }

    public List<HomeworkItemVO> getTeacherHomeworkPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle) {
        Page<HomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return homeworkMapper.getTeacherHomeworkPage(page, teacherId, homeworkId, homeworkTitle).getRecords();
    }

    public HomeworkItemVO getHomeworkItemVO(Long homeworkId) {
        return homeworkMapper.getHomeworkItemVO(homeworkId);
    }
}