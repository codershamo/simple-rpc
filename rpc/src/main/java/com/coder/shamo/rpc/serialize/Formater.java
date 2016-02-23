/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.serialize;

/**
 * @author xuefeng.sha  Date: 2016/1/30 Time: 13:44
 * @version $Id$
 */
public interface Formater {

    /**
     *
     * @param clazz
     * @param method
     * @param param
     * @return
     */
    String reqFormater(Class clazz, String method, Object param);

    /**
     *
     * @param param
     * @return
     */
    String rspFormater(Object param);
}
