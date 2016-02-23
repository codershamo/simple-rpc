/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.provider;

import com.coder.shamo.api.User;
import com.coder.shamo.api.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xuefeng.sha  Date: 2016/2/23 Time: 13:10
 * @version $Id$
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    public String print(User user) {
//        System.out.println(user.toString());
        return user.toString();
    }
}
