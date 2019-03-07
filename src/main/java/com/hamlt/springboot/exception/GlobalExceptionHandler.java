package com.hamlt.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler  {

    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     */
    @ExceptionHandler(CustomException.class)
    public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException exception = (CustomException) e;
        return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
        //return exception.getMessage();
    }

    /**
     * 捕获  ArithmeticException 异常
     *   如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     *   那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     * @return 返回到一个页面
     */
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        return modelAndViewOfSyncError(e);
    }


    /**
     *  整合同步请求与异步请求异常处理
     */
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
       if(isAsyncRequest(request)) {
           return new ErrorResponseEntity(1001, "异步请求异常");
       } else {
           return modelAndViewOfSyncError(e);
       }
    }

    /**
     *  获取同步请求异常视图返回到错误页面
     */
    private ModelAndView modelAndViewOfSyncError(final Exception e) {
        RuntimeException exception = (RuntimeException) e;
        ModelAndView view = new ModelAndView();
        view.setViewName("errorPage");
        ErrorResponseEntity entity = new ErrorResponseEntity(1001, exception.getMessage());
        return  view.addObject("info", entity);
    }

    /**
     *  判断是否异步请求
     * @return  boolean
     */
    private  boolean isAsyncRequest(HttpServletRequest request){
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }

}