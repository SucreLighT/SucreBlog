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
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(String userName, String password);

    /**
     * 根据用户ID获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserById(Integer loginUserId);


    /**
     * 修改用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改用户名
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateUserName(Integer loginUserId, String loginUserName, String nickName);
}
