package cn.lanedy.utils;

import java.io.Serializable;

/**
 * Created by liyue
 * Time 2019-03-01 15:12
 */
public class JsonVO implements Serializable {
    private boolean success;
    private String msg;
    private Object o;

    public JsonVO() {
    }

    public JsonVO(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonVO(boolean success, String msg, Object o) {
        this.success = success;
        this.msg = msg;
        this.o = o;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
