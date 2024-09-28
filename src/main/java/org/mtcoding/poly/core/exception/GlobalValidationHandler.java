package org.mtcoding.poly.core.exception;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.mtcoding.poly.core.exception.api.ExceptionApi400;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


@Component
@Aspect
public class GlobalValidationHandler {

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void validCheck(JoinPoint jp) {
        System.out.println("로깅테스트");
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
}
