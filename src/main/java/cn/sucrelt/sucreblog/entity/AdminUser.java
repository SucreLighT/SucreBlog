package cn.sucrelt.sucreblog.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户实体类，对应数据库中表tab_admin_user
 * @author: sucre
 * @date: 2020/12/28
 * @time: 10:52
 */


public class AdminUser {
    /**
     * 管理员id
     */
    @Getter
    @Setter
    private Integer adminUserId;

    /**
     * 管理员登录名
     */
    @Getter
    @Setter
    private String loginUserName;

    /**
     * 管理员登录密码
     */
    @Getter
    @Setter
    private String loginPassword;

    /**
     * 管理员登录密码
     */
    @Getter
    @Setter
    private String nickName;

    /**
     * 账号是否锁定 1为锁定0为未锁定 锁定后无法登录
     */
    @Getter
    @Setter
    private Byte locked;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminUserId=").append(adminUserId);
        sb.append(", loginUserName=").append(loginUserName);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", nickName=").append(nickName);
        sb.append(", locked=").append(locked);
        sb.append("]");
        return sb.toString();
    }
}
