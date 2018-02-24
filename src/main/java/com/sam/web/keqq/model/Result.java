package com.sam.web.keqq.model;

/**
 * @author xiads
 * @date 19/01/2018
 * @since
 */
public class Result<T> {

    private int retcode;
    private T result;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "retcode='" + retcode + '\'' +
                ", result=" + result +
                '}';
    }
}
