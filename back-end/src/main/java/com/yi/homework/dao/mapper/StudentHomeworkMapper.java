package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yi.homework.model.entity.StudentHomework;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentHomeworkMapper extends BaseMapper<StudentHomework> {

    Integer count(Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    IPage<StudentHomeworkItemVO> getPage(IPage<StudentHomeworkItemVO> page, Long teacherId, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    Integer countWithTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    IPage<StudentHomeworkItemVO> getPageWithTeacherComment(IPage<com.yi.homework.model.vo.response.table.StudentHomeworkItemVO> page, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    Integer countWithoutTeacherComment(Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    IPage<StudentHomeworkItemVO> getPageWithoutTeacherComment(IPage<com.yi.homework.model.vo.response.table.StudentHomeworkItemVO> page, Long homeworkId, String homeworkTitle, Long studentId, String studentName);

    StudentHomeworkItemVO getStudentHomework(Long studentId, Long homeworkId);
}