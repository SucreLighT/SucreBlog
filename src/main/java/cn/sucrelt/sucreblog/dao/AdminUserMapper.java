package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 用户Dao接口
 * @author: sucre
 * @date: 2020/12/28
 * @time: 10:58
 */

public interface AdminUserMapper {

    /**
     * 用户登录方法
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据id查询用户
     *
     * @param adminUserId
     * @return
     */
    AdminUser selectById(Integer adminUserId);

    /**
     * 根据id检索并更新用户信息
     *
     * @param adminUser
     * @return
     */
    int updateByIdSelective(AdminUser adminUser);
}
