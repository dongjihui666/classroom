package com.dong.servicebase.exceptionhandler;

import com.dong.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.HandlerChain;

/**
 * 统一异常处理类
 */

/**
 * //@ControllerAdvice，是Spring3.2提供的新注解,它是一个Controller增强器,可对controller中被
 * //@RequestMapping注解的方法加一些逻辑处理。最常用的就是异常处理
 * 统一异常处理
 * 需要配合@ExceptionHandler使用。
 * 当将异常抛到controller时,可以对异常进行统一处理,规定返回的json格式或是跳转到一个错误页面
 */
@ControllerAdvice
@Slf4j //表示里面用到SL44J
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();//打印异常
        return R.error().message("执行了全局异常处理..");
    }
    //特定异常
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException自定义异常");
    }
    //添加自定义异常
    @ExceptionHandler(value = GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        log.error(e.getMessage());//异常输出语句
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }

}
