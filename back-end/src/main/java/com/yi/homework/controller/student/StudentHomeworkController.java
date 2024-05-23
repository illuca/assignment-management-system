package com.yi.homework.controller.student;

import com.yi.homework.controller.BaseController;
import com.yi.homework.model.vo.response.ResultVO;
import com.yi.homework.model.vo.response.table.StudentHomeworkItemVO;
import com.yi.homework.service.student.StudentHomeworkService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student/homework")
public class StudentHomeworkController extends BaseController {

    private final StudentHomeworkService studentHomeworkService;

    public StudentHomeworkController(StudentHomeworkService studentHomeworkService) {
        this.studentHomeworkService = studentHomeworkService;
    }

    /**
     * 提交作业
     *
     * @param studentHomeworkItemVO
     * @return
     */
    @PostMapping
    public ResultVO submitHomework(@Validated @RequestBody StudentHomeworkItemVO studentHomeworkItemVO) {
        return studentHomeworkService.submitStudentHomework(studentHomeworkItemVO);
    }

    /**
     * 根据作业编号获取作业详情
     *
     * @param homeworkId
     * @return
     */
    @GetMapping("/{homeworkId}")
    public ResultVO getHomework(@PathVariable("homeworkId") Long homeworkId) {
        return studentHomeworkService.getHomework(homeworkId);
    }

    /**
     * 返回总页数
     *
     * @param homeworkId
     * @param homeworkTitle
     * @return
     */
    @RequestMapping("/page/count")
    public ResultVO getPageCount(Long homeworkId, String homeworkTitle) {
        return studentHomeworkService.getPageCount(homeworkId, homeworkTitle);
    }

    /**
     * 搜索作业展示分页结果后,根据请求,返回指定页面结果
     *
     * @param index
     * @param homeworkId
     * @param homeworkTitle
     * @return 如果homeworkId和homeworkTitle都为空, 则不是搜索<br>
     * 否则返回搜索结果
     */
    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, Long homeworkId, String homeworkTitle) {
        return studentHomeworkService.getPage(index, homeworkId, homeworkTitle);
    }
}