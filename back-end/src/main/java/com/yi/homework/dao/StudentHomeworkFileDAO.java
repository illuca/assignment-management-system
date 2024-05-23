package com.yi.homework.dao;

import com.yi.homework.dao.mapper.StudentHomeworkFileMapper;
import com.yi.homework.model.entity.StudentHomeworkFile;
import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentHomeworkFileDAO extends BaseDAO {

    private final StudentHomeworkFileMapper studentHomeworkFileMapper;

    public StudentHomeworkFileDAO(StudentHomeworkFileMapper studentHomeworkFileMapper) {
        this.studentHomeworkFileMapper = studentHomeworkFileMapper;
    }

    public StudentHomeworkFileItemVO getFileInfo(Long studentId, Long homeworkId) {
        return studentHomeworkFileMapper.getFileInfo(studentId, homeworkId);
    }

    public int save(StudentHomeworkFile studentHomeworkFile) {
        return studentHomeworkFileMapper.insert(studentHomeworkFile);
    }
}