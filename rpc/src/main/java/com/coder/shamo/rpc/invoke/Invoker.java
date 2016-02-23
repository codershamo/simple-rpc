/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.invoke;

import com.coder.shamo.rpc.exception.RpcException;

import java.io.OutputStream;

/**
 * @author xuefeng.sha  Date: 2016/2/22 Time: 16:02
 * @version $Id$
 */
public interface Invoker {
    /**
     * 调用请求
     * @param request 请求报文
     * @param consumerConfig 消费者配置
     * @return
     * @throws RpcException
     */
    String request(String request, ConsumerConfig consumerConfig) throws RpcException;

    /**
     * 请求应答
     * @param response 响应报文
     * @param outputStream 输出流
     * @throws RpcException
     */
    void response(String response, OutputStream outputStream) throws RpcException;
}
