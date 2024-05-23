package com.yi.homework.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.homework.model.entity.StudentHomeworkFile;
import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentHomeworkFileMapper extends BaseMapper<StudentHomeworkFile> {

    @Select("select student_homework_file_id,student_id,homework_id,file_id,file_name,file_path from student_homework_file where student_id=#{studentId} and homework_id = #{homeworkId}")
    StudentHomeworkFileItemVO getFileInfo(@Param("studentId") Long studentId, @Param("homeworkId") Long homeworkId);
}