package com.zc.entity;

import com.zc.consts.ResultCode;
import lombok.Data;

/**
 * @author ZhangC
 * @create 2021-08-03-17:45
 */
@Data
public class ResultResponse {
    private int code;
    private String msg;
    private Object data;


    public ResultResponse(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public ResultResponse(ResultCode resultCode,Object data){
        this.code=resultCode.code();
        this.msg=resultCode.message();
        this.data=data;
    }

    public static ResultResponse success(){
        return new ResultResponse(ResultCode.SUCCESS,null);
    }
    public static ResultResponse success(Object data){
        return new ResultResponse(ResultCode.SUCCESS,data);
    }

    public static ResultResponse error(){
        return new ResultResponse(ResultCode.ERROR,null);
    }

}
