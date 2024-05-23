package com.yi.homework.controller.student;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.service.student.StudentCommentedService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student/commented")
public class StudentCommentedController extends BaseController {

    private final StudentCommentedService studentCommentedService;

    public StudentCommentedController(StudentCommentedService studentCommentedService) {
        this.studentCommentedService = studentCommentedService;
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return studentCommentedService.getPageCount(homeworkId, homeworkTitle);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return studentCommentedService.getPage(index, homeworkId, homeworkTitle);
    }

}