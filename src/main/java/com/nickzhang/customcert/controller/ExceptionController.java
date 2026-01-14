package com.nickzhang.customcert.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张骏山
 * @Date: 2026/1/14 23:24
 * @PackageName: com.nickzhang.customcert.controller
 * @ClassName: ExceptionController
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        String message = e.getMessage();
        if (e.getCause() != null) {
            message += "\n" + e.getCause().getMessage();
        }
        return message;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        String message = e.getMessage();
        for (StackTraceElement element : e.getStackTrace()) {
            message += "\n" + element.toString();
        }
        return message;
    }


}
