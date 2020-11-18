package org.zgy.srping.mytest;

import org.junit.Test;
import org.zgy.srping.controller.UserController;
import org.zgy.srping.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyTest {

    @Test
    public void test() throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        // 创建对象
        UserService userService = new UserService();
        System.out.println(userService);
        // 获取userController中的所有属性
        Field serviceFile = clazz.getDeclaredField("userService");
        serviceFile.setAccessible(true);
        // 设置属性值 -> 通过set方法 -> 根据get/set生成规则，需要拼接set方法
        String name = serviceFile.getName();
        // 拼接方法的名称
        name = name.substring(0, 1).toUpperCase() + name.substring(1); // userService -> UserService
        String setMethodName = "set" + name; // setUserService
        // 通过方法注入属性的对象
        Method method = clazz.getMethod(setMethodName, UserService.class);
        // 反射
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());
    }

}
