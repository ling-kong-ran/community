package com.xiaoling.community.exception;

public enum MyExceptionCodeEnum implements  IMyExceptionCode{
    QUESTION_NOT_FOUND(2001,"你找的问题不在啦" ),
    COMMENT_TYPE_NOT_CHOICE(2002,"未选择任何类型,您不能评论"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    ;
    String msg;
    Integer code;

    MyExceptionCodeEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
