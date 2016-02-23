/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.proxy;

import com.coder.shamo.rpc.container.Container;
import com.coder.shamo.rpc.container.HttpContainer;
import com.coder.shamo.rpc.exception.RpcException;
import com.coder.shamo.rpc.exception.RpcExceptionCodeEnum;
import com.coder.shamo.rpc.invoke.HttpInvoker;
import com.coder.shamo.rpc.invoke.Invoker;
import com.coder.shamo.rpc.invoke.ProviderConfig;
import com.coder.shamo.rpc.serialize.Formater;
import com.coder.shamo.rpc.serialize.Parser;
import com.coder.shamo.rpc.serialize.Request;
import com.coder.shamo.rpc.serialize.json.JsonFormater;
import com.coder.shamo.rpc.serialize.json.JsonParser;
import org.mortbay.jetty.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuefeng.sha  Date: 2016/2/22 Time: 16:58
 * @version $Id$
 */
public class ProviderProxyFactory extends AbstractHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProviderProxyFactory.class);
    private Map<Class, Object> providers = new ConcurrentHashMap<Class, Object>();
    private static ProviderProxyFactory factory;
    private Parser parser = JsonParser.parser;
    private Formater formater = JsonFormater.formater;
    private Invoker invoker = HttpInvoker.invoker;

    public ProviderProxyFactory(Map<Class, Object> providers) {
        if(Container.container == null){
            new HttpContainer(this).start();
        }
        for(Map.Entry<Class, Object> entry : providers.entrySet()){
            register(entry.getKey(), entry.getValue());
        }
        factory = this;
    }

    public ProviderProxyFactory(Map<Class, Object> providers, ProviderConfig providerConfig){
        if(Container.container == null){
            new HttpContainer(this, providerConfig).start();
        }
        for(Map.Entry<Class, Object> entry : providers.entrySet()){
            register(entry.getKey(), entry.getValue());
        }
        factory = this;
    }

    public void register(Class clazz, Object object){
        providers.put(clazz, object);
        logger.info("{} 已经发布", clazz.getSimpleName());
    }

    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        String reqStr = request.getParameter("data");
        try{
            Request rpcRequest = parser.reqParser(reqStr);
            Object result = rpcRequest.invoke(ProviderProxyFactory.getInstance().getBeanByClass(rpcRequest.getClazz()));
            invoker.response(formater.rspFormater(result), response.getOutputStream());
        } catch (RpcException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBeanByClass(Class clazz) throws RpcException{
        Object bean = providers.get(clazz);
        if(bean != null){
            return bean;
        }
        throw new RpcException(RpcExceptionCodeEnum.NO_BEAN_FOUND.getCode(), clazz);
    }

    public static ProviderProxyFactory getInstance(){
        return factory;
    }
}
