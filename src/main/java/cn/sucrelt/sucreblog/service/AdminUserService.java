package cn.sucrelt.sucreblog.service;

import cn.sucrelt.sucreblog.entity.AdminUser;

/**
 * @description: 用户Service接口
 * @author: sucre
 * @date: 2020/12/28
 * @time: 11:03
 */
public interface AdminUserService {
    /**
     * 用户登录方法
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(String userName, String password);
}
