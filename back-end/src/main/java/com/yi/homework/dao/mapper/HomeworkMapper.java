package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yi.homework.model.entity.Homework;
import com.yi.homework.model.vo.response.table.HomeworkItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {

    Integer count(Long homeworkId, String homeworkTitle);

    IPage<HomeworkItemVO> getPage(IPage<HomeworkItemVO> page, Long homeworkId, String homeworkTitle);

    Integer countStudentRestHomework(Long studentId, Long homeworkId, String homeworkTitle);

    IPage<HomeworkItemVO> getStudentRestHomeworkPage(IPage<HomeworkItemVO> page, Long studentId, Long homeworkId, String homeworkTitle);

    Integer countTeacherHomework(Long teacherId, Long homeworkId, String homeworkTitle);

    IPage<HomeworkItemVO> getTeacherHomeworkPage(Page<HomeworkItemVO> page, Long teacherId, Long homeworkId, String homeworkTitle);

    HomeworkItemVO getHomeworkItemVO(Long homeworkId);
}