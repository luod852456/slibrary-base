package com.chaolu.slibrary.model;

import java.io.Serializable;

/**
 * "result": {
 *"success": true
 *}*/
@SuppressWarnings("serial")
public class BaseRespon implements Serializable {
    //返回数据形式--成功、失败
    //失败提示内容
    private String msg,code;

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess(){
        if (code.equalsIgnoreCase("success")){
            return true;
        }else if (code.equalsIgnoreCase("1")){
            return true;
        }else {
            return false;
        }
    }
}
