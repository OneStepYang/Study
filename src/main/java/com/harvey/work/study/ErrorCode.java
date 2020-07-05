package com.harvey.work.study;

public enum ErrorCode {
    Err_404(404,"服务器错误"),Err_200(200,"请求成功");
    private  int code;
    private String info;
    ErrorCode(int code,String info){
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
