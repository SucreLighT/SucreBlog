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
}
