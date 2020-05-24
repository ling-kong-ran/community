package com.xiaoling.community.exception;

import lombok.Data;

@Data
public class MyExceptionCode extends RuntimeException{
    private long code;
    private String msg;

    public MyExceptionCode(Long code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyExceptionCode(String msg){
        super(msg);
        this.msg = msg;
    }
}

