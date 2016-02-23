/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.consumer;

import com.coder.shamo.api.User;
import com.coder.shamo.api.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xuefeng.sha  Date: 2016/2/23 Time: 14:32
 * @version $Id$
 */
@Service("userController")
public class UserController {
    @Resource
    private UserService userService;

    public String print(String name, int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return userService.print(user);
    }
}
