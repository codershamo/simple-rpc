/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.proxy;

import com.coder.shamo.rpc.invoke.ConsumerConfig;
import com.coder.shamo.rpc.invoke.HttpInvoker;
import com.coder.shamo.rpc.invoke.Invoker;
import com.coder.shamo.rpc.serialize.Formater;
import com.coder.shamo.rpc.serialize.Parser;
import com.coder.shamo.rpc.serialize.json.JsonFormater;
import com.coder.shamo.rpc.serialize.json.JsonParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuefeng.sha  Date: 2016/2/23 Time: 14:23
 * @version $Id$
 */
public class ConsumerProxyFactory implements InvocationHandler {
    private ConsumerConfig consumerConfig;
    private Parser parser = JsonParser.parser;
    private Formater formater = JsonFormater.formater;
    private Invoker invoker = HttpInvoker.invoker;
    private String clazz;

    public Object create() throws Exception{
        Class interfaceClass = Class.forName(clazz);
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class interfaceClass = proxy.getClass().getInterfaces()[0];
        String req = formater.reqFormater(interfaceClass, method.getName(), args[0]);
        String resp = invoker.request(req, consumerConfig);
        return parser.rspParser(resp);
    }

    public ConsumerConfig getConsumerConfig() {
        return consumerConfig;
    }

    public void setConsumerConfig(ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Formater getFormater() {
        return formater;
    }

    public void setFormater(Formater formater) {
        this.formater = formater;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
