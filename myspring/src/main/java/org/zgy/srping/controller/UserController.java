package org.zgy.srping.controller;

import org.zgy.srping.mytest.AutoWired;
import org.zgy.srping.service.UserService;

public class UserController {

    @AutoWired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
