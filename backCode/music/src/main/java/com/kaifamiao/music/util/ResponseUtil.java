package com.kaifamiao.music.util;

import lombok.Data;

@Data
public class ResponseUtil {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseUtil(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
