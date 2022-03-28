package com.loki.dto;


import lombok.Data;

import java.io.Serializable;

@Data
//统一后端返回的数据
public class Result implements Serializable {
    //状态码 200--正常
    private String code;
    private String msg;
    private Object data;

    //二次封装，返回对前端重要的数据
    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("200");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }
    public static Result succ(int i, String mess, Object data) {
        Result m = new Result();
        m.setCode("200");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("401");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }
    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("401");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
    public static Result fail(int i, String mess, Object data){
        Result m = new Result();
        m.setCode("404");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

}
