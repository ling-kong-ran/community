package com.xiaoling.community.exception;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionController {
    @ResponseBody//返回的json数据
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 400);
        //判断异常的类型,返回不一样的返回值
        if (ex instanceof MissingServletRequestParameterException) {
            map.put("msg", "缺少必需参数：" + ((MissingServletRequestParameterException) ex).getParameterName());
        } else if (ex instanceof MyExceptionCode) {
            map.put("msg", "服务器冒烟了");
        }
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
