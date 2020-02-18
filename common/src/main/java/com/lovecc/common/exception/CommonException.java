package com.lovecc.common.exception;

public class CommonException extends RuntimeException {
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private ErrorCode errorCode;
    public CommonException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.errorCode=errorCode;
    }
    public CommonException(ErrorCode errorCode,String msg){
        super(msg);
        this.errorCode=errorCode;
    }
    public int getCode(){
        return errorCode.getCode();
    }
    public String getMsg(){
        return errorCode.getMsg();
    }
}
