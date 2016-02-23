/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.serialize;

import java.io.Serializable;

/**
 * @author xuefeng.sha  Date: 2016/1/30 Time: 13:51
 * @version $Id$
 */
public class Request implements Serializable{
    private Class clazz;
    private String method;
    private Object param;

    public Request() {
    }

    public Request(Class clazz, String method, Object param) {
        this.clazz = clazz;
        this.method = method;
        this.param = param;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Object invoke(Object bean) throws Exception{
        return clazz.getMethod(method, param.getClass()).invoke(bean, param);
    }
}
