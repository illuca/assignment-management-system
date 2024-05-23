package com.yi.homework.aspect;

import com.yi.homework.model.constant.HttpStatusCode;
import com.yi.homework.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
public class FailedResultCodeAspect {

    @Pointcut("execution(public com.yi.homework.model.vo.response.ResultVO " + "com.yi.homework.controller..*.*(..))")
    public void controllerResult() {
    }

    @AfterReturning(value = "controllerResult()", returning = "resultVO")
    public Object afterReturning(ResultVO resultVO) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (servletRequestAttributes == null) {
            return resultVO;
        }

        HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();

        if (httpServletResponse == null) {
            return resultVO;
        }

        if (resultVO.getCode() == ResultVO.FAIL) {
            httpServletResponse.setStatus(HttpStatusCode.NOT_ACCEPTABLE);
        }

        return resultVO;
    }

}