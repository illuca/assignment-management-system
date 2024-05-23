package com.yi.homework.service.student;

import com.yi.homework.manager.student.StudentCommentedManager;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.BaseService;
import org.springframework.stereotype.Service;


@Service
public class StudentCommentedService extends BaseService {

    private final StudentCommentedManager studentCommentedManager;

    public StudentCommentedService(StudentCommentedManager studentCommentedManager) {
        this.studentCommentedManager = studentCommentedManager;
    }

    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return result(studentCommentedManager.getPageCount(homeworkId, homeworkTitle, getUserId()));
    }

    public ResultVO getPage(Integer index, Long homeworkId, String homeworkTitle) {
        return result(studentCommentedManager.getPage(index, homeworkId, homeworkTitle, getUserId()));
    }

}