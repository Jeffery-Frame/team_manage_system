package com.wanxi.springboot.team.manage.system.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;
    private long count;

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success(T data, long count) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, count);
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }
    public static <T> CommonResult<T> success(T data, String message, long count) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data, count);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    public static <T> CommonResult<T> unauthorized() {
        return failed(ResultCode.UNAUTHORIZED);
    }

    public static <T> CommonResult<T> unauthorized(String message) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), message, null);
    }

    public static <T> CommonResult<T> forbidden() {
        return failed(ResultCode.FORBIDDEN);
    }

    public static <T> CommonResult<T> forbidden(String message) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), message, null);
    }
}
