/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.container;

/**
 * @author xuefeng.sha  Date: 2016/2/1 Time: 17:08
 * @version $Id$
 */
public abstract class Container {
    public static volatile boolean isStart = false;

    public abstract void start();

    public static volatile Container container = null;
}
