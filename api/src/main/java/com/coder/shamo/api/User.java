/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.api;

/**
 * @author xuefeng.sha  Date: 2016/2/23 Time: 12:23
 * @version $Id$
 */
public class User {
    private String name;
    private int age;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
