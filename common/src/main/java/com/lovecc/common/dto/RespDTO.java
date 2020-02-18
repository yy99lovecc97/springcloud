package com.lovecc.common.dto;

import java.io.Serializable;

public class RespDTO<T> implements Serializable {
    public int code = 0;
    public String error = "";
    public T data;

    public static RespDTO onSuc(Object data){
        RespDTO respDTO = new RespDTO();
        respDTO.data = data;
        return respDTO;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
