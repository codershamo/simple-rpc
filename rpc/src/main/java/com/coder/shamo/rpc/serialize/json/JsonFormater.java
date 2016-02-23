/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.coder.shamo.rpc.serialize.Formater;
import com.coder.shamo.rpc.serialize.Request;

/**
 * @author xuefeng.sha  Date: 2016/1/30 Time: 13:55
 * @version $Id$
 */
public class JsonFormater implements Formater {

    public String reqFormater(Class clazz, String method, Object param) {
        Request request = new Request();
        request.setParam(param);
        request.setClazz(clazz);
        request.setMethod(method);
        return JSON.toJSONString(request, SerializerFeature.WriteClassName);

    }

    public String rspFormater(Object param) {
        return JSON.toJSONString(param, SerializerFeature.WriteClassName);
    }
}
