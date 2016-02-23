/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.rpc.serialize;

import com.coder.shamo.rpc.exception.RpcException;

/**
 * @author xuefeng.sha  Date: 2016/1/30 Time: 13:46
 * @version $Id$
 */
public interface Parser {

    Request reqParser(String param) throws RpcException;

    <T> T rspParser(String result);
}
