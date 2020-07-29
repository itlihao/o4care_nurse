package com.o4care.nurse.net;
import java.io.Serializable;

public class BaseEntity<E> implements Serializable {

    /**
     * 如果该值返回0，表示返回错误。1，则表示返回成功。
     */
    private boolean isOk;
    private int status = -1;
    private int total = -1;
    private E data;
    private E rows;
    /**
     * errorCode：错误类型
     * errMsg：针对错误类型的消息说明。
     */
    private String errorCode;
    private String errMsg;

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public E getData() {
        return data;
    }
    public void setData(E data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public E getRows() {
        return rows;
    }
    public void setRows(E rows) {
        this.rows = rows;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isOk() {
        return isOk;
    }
    public void setOk(boolean ok) {
        isOk = ok;
    }
}
