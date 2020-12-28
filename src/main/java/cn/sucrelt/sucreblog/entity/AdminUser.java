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
    @Getter
    @Setter
    private Integer adminUserId;

    @Getter
    @Setter
    private String loginUserName;

    @Getter
    @Setter
    private String loginPassword;

    @Getter
    @Setter
    private String nickName;

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
