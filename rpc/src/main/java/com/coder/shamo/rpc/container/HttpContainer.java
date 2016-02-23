/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.container;

import com.coder.shamo.rpc.invoke.ProviderConfig;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuefeng.sha  Date: 2016/2/1 Time: 17:21
 * @version $Id$
 */
public class HttpContainer extends Container {
    private static final Logger logger = LoggerFactory.getLogger(HttpContainer.class);

    private AbstractHandler httpHandler;
    private ProviderConfig providerConfig;

    public HttpContainer(AbstractHandler httpHandler) {
        this(httpHandler, new ProviderConfig("/invoke", 8080));
    }

    public HttpContainer(AbstractHandler httpHandler, ProviderConfig providerConfig) {
        this.httpHandler = httpHandler;
        this.providerConfig = providerConfig;
        Container.container = this;
    }

    public void start(){
        Server server = new Server();
        try{
            SelectChannelConnector connector = new SelectChannelConnector();
            connector.setPort(providerConfig.getPort());
            server.setConnectors(new Connector[]{connector});
            server.setHandler(httpHandler);
            server.start();
        }catch (Throwable e){
            logger.error(" 服务启动失败", e);
        }
    }
}
