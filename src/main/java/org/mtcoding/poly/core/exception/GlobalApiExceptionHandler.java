package org.mtcoding.poly.core.exception;


import org.mtcoding.poly.core.exception.api.ExceptionApi400;
import org.mtcoding.poly.core.exception.api.ExceptionApi404;
import org.mtcoding.poly.core.exception.api.ExceptionApi500;
import org.mtcoding.poly.core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalApiExceptionHandler {
    

    @ExceptionHandler(ExceptionApi400.class)
    public ResponseEntity<?> ex400(Exception e) {
        return new ResponseEntity<>(Resp.fail(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExceptionApi404.class)
    public ResponseEntity<?> ex404(Exception e) {
        return new ResponseEntity<>(Resp.fail(404, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceptionApi500.class)
    public ResponseEntity<?> ex500(Exception e) {
        return new ResponseEntity<>(Resp.fail(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e) {
         return new ResponseEntity<>(Resp.fail(500,"알 수 없는 에러입니다."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}







