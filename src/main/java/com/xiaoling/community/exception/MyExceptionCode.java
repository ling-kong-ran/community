package com.xiaoling.community.exception;

public class MyExceptionCode extends RuntimeException {
    private Integer code;
    private String msg;

    public MyExceptionCode(IMyExceptionCode iMyExceptionCode){
        this.code = iMyExceptionCode.getCode();
        this.msg = iMyExceptionCode.getMsg();
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

