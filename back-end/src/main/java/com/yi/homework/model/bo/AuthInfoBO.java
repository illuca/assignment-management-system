package com.yi.homework.model.bo;

import com.yi.homework.model.constant.UserType;
import com.yi.homework.model.entity.Student;
import com.yi.homework.model.entity.Teacher;
import lombok.Data;


@Data
public class AuthInfoBO {

    private Long userId;

    private String username;

    private String password;

    private Integer userType;

    private AuthInfoBO(Long userId, String username, String password, Integer userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public static AuthInfoBO fromStudent(Student student) {
        if (student == null) {
            return null;
        }

        return new AuthInfoBO(student.getStudentId(), student.getStudentName(), student.getPassword(), UserType.STUDENT);
    }

    public static AuthInfoBO fromTeacher(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        return new AuthInfoBO(teacher.getTeacherId(), teacher.getTeacherName(), teacher.getPassword(), UserType.TEACHER);
    }

}