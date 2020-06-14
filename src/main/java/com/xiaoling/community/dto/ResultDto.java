package com.xiaoling.community.dto;

import com.xiaoling.community.exception.MyExceptionCodeEnum;
import lombok.Data;

@Data
public class ResultDto {
    private Integer code;
    private String message;
    public static ResultDto errorOf(Integer code,String message){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return  resultDto;
    }

    public static ResultDto errorOf(MyExceptionCodeEnum noLogin) {
        return errorOf(noLogin.getCode(),noLogin.getMsg());
    }

    public static ResultDto okof() {
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return  resultDto;
    }
}
