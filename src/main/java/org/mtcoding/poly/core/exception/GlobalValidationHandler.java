package org.mtcoding.poly.core.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.mtcoding.poly.core.exception.api.ExceptionApi400;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


@Component
@Aspect
public class GlobalValidationHandler {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void checkValidation(JoinPoint jp) {

        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    for (FieldError error : errors.getFieldErrors()) {
                        throw new ExceptionApi400(error.getDefaultMessage() + " : " + error.getField());
                    }
                }
            }
        }
    }



    @Before("within(org.mtcoding.poly.user.UserController) && @within(org.springframework.stereotype.Controller)")
    public void writeLog(JoinPoint jp) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();

        String clientAgent = request.getHeader("User-Agent");
        String what = "";

        if(method.isAnnotationPresent(GetMapping.class)) {
            what = "조회";
        }
        if(method.isAnnotationPresent(PostMapping.class)) {
            what = "등록";
        }
        if(method.isAnnotationPresent(PutMapping.class)) {
            what = "수정";
        }

        System.out.println("[user"+ what +"]  ClientAgent: " + clientAgent);
    }




}
