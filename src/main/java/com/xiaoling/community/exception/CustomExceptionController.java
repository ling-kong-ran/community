package com.xiaoling.community.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionController  {
    @ResponseBody//返回的json数据
    @ExceptionHandler(value = Exception.class)
    public Map<Object, Object> errorHandler(MyExceptionCode ex) {
        Map<Object,Object> map = new HashMap<>();
        map.put(ex.getCode(),ex.getMsg());
        return map;
    }
    @ExceptionHandler(value = MyExceptionCode.class)
    public ModelAndView myErrorHandler(MyExceptionCode ex) {
        ModelAndView modelAndView = new ModelAndView();
        //指定错误页面的模板页
        modelAndView.setViewName("error");
            modelAndView.addObject("code", ex.getCode());
            modelAndView.addObject("msg", ex.getMsg());


        return modelAndView;
    }

}
