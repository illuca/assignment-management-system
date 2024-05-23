package com.yi.homework.dao;

import com.yi.homework.model.vo.response.table.StudentHomeworkFileItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentHomeworkFileDAOTest {

    @Autowired
    StudentHomeworkFileDAO studentHomeworkFileDAO;

    @Test
    void getFileInfo() {

        StudentHomeworkFileItemVO fileInfo = studentHomeworkFileDAO.getFileInfo(17002L, 11L);
        System.out.println("fileInfo = " + fileInfo);
    }
}