/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.coder.shamo.rpc.exception.RpcException;
import com.coder.shamo.rpc.exception.RpcExceptionCodeEnum;
import com.coder.shamo.rpc.serialize.Parser;
import com.coder.shamo.rpc.serialize.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuefeng.sha  Date: 2016/2/1 Time: 16:45
 * @version $Id$
 */
public class JsonParser implements Parser {
    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);
    public static final Parser parser = new JsonParser();
    public Request reqParser(String param) throws RpcException {
        try{
            logger.debug("调用参数 {}", param);
            return JSON.parseObject(param, Request.class);
        }catch(Exception e){
            logger.error("参数转换异常: param = {}", param, e);
            throw new RpcException("", e, RpcExceptionCodeEnum.DATA_PARSER_ERROR.getCode(), param);
        }
    }

    public <T> T rspParser(String result) {
        return (T) JSON.parse(result);
    }
}
