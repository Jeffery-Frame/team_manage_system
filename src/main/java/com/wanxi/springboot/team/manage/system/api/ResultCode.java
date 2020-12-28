package com.wanxi.springboot.team.manage.system.api;


public enum ResultCode implements IErrorCode {
    SUCCESS(200,"success"),
    FAILED(500,"failed"),
    VALIDATE_FAILED(404,"validate failed"),
    UNAUTHORIZED(401,"unauthorized"),
    FORBIDDEN(403,"forbidden");
    private long code;
    private String message;
    ResultCode(long code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
