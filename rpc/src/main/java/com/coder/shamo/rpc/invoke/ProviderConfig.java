/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.invoke;

/**
 * @author xuefeng.sha  Date: 2016/2/1 Time: 17:22
 * @version $Id$
 */
public class ProviderConfig {
    private String target;
    private Integer port;

    public ProviderConfig() {
    }

    public ProviderConfig(String target, Integer port) {
        this.target = target;
        this.port = port;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
