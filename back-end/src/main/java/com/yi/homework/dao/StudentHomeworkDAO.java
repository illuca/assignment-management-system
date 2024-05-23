package com.yi.homework.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.dao.mapper.StudentHomeworkMapper;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudentHomeworkDAO extends BaseDAO {

    public static final int PAGE_SIZE = 7;

    private final StudentHomeworkMapper studentHomeworkMapper;

    public StudentHomeworkDAO(StudentHomeworkMapper studentHomeworkMapper) {
        this.studentHomeworkMapper = studentHomeworkMapper;
    }

    public int insert(StudentHomework studentHomework) {
        return studentHomeworkMapper.insert(studentHomework);
    }

    public int deleteById(Long studentHomeworkId) {
        return studentHomeworkMapper.deleteById(studentHomeworkId);
    }

    public int updateById(StudentHomework studentHomework) {
        return studentHomeworkMapper.updateById(studentHomework);
    }

    public StudentHomework selectById(Long studentHomeworkId) {
        return studentHomeworkMapper.selectById(studentHomeworkId);
    }

    public int count(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkMapper.count(teacherId, homeworkId, homeworkTitle, studentId, studentName);
    }

    public List<StudentHomeworkItemVO> getPage(Integer index, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return studentHomeworkMapper.getPage(page, teacherId, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    public int countWithTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkMapper.countWithTeacherComment(homeworkId, homeworkTitle, studentId, studentName);
    }

    public List<StudentHomeworkItemVO> getPageWithTeacherComment(Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return studentHomeworkMapper.getPageWithTeacherComment(page, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    public int countWithoutTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        return studentHomeworkMapper.countWithoutTeacherComment(homeworkId, homeworkTitle, studentId, studentName);
    }

    public List<StudentHomeworkItemVO> getPageWithoutTeacherComment(Integer index, Long homeworkId, String homeworkTitle, Long studentId, String studentName) {
        Page<StudentHomeworkItemVO> page = new Page<>(index, PAGE_SIZE);
        return studentHomeworkMapper.getPageWithoutTeacherComment(page, homeworkId, homeworkTitle, studentId, studentName).getRecords();
    }

    public StudentHomeworkItemVO getStudentHomeworkVO(Long studentId, Long homeworkId) {
        return studentHomeworkMapper.getStudentHomework(studentId, homeworkId);
    }

    public int deleteStudentHomeworkByHomeworkId(Long homeworkId) {
        LambdaQueryWrapper<StudentHomework> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StudentHomework::getHomeworkId, homeworkId);
        return studentHomeworkMapper.delete(lambdaQueryWrapper);
    }

}