package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.AdminUserMapper;
import cn.sucrelt.sucreblog.entity.AdminUser;
import cn.sucrelt.sucreblog.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户Service接口实现类
 * @author: sucre
 * @date: 2020/12/28
 * @time: 11:05
 */

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName, password);
    }

    @Override
    public AdminUser getUserById(Integer loginUserId) {
        return adminUserMapper.selectById(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectById(loginUserId);
        if (adminUser != null) {
            // 比较密码是否相同
            if (originalPassword.equals(adminUser.getLoginPassword())) {
                adminUser.setLoginPassword(newPassword);
                if (adminUserMapper.updateByIdSelective(adminUser) > 0) {
                    // 修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateUserName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectById(loginUserId);
        if (adminUser != null) {
            // 用户名和昵称至少有一个需要更新，否则修改失败
            if (!adminUser.getLoginUserName().equals(loginUserName) || !adminUser.getNickName().equals(nickName)) {
                adminUser.setLoginUserName(loginUserName);
                adminUser.setNickName(nickName);
                if (adminUserMapper.updateByIdSelective(adminUser) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
