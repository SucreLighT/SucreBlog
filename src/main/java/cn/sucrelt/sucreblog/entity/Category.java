package cn.sucrelt.sucreblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description: 分类实体类，对应数据库表tb_blog_category
 * @author: sucre
 * @date: 2021/01/05
 * @time: 14:21
 */
public class Category {
    /**
     * 分类id
     */
    @Setter
    @Getter
    private Integer categoryId;

    /**
     * 分类名称
     */
    @Setter
    @Getter
    private String categoryName;

    /**
     * 分类图标
     */
    @Setter
    @Getter
    private String categoryIcon;

    /**
     * 分类排序值，值越大表示使用次数越多
     */
    @Setter
    @Getter
    private Integer categoryRank;

    /**
     * 逻辑删除标志，值为1表示逻辑删除
     */
    @Setter
    @Getter
    private Byte isDeleted;

    /**
     * 分类创建日期
     */
    @Setter
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", categoryIcon=").append(categoryIcon);
        sb.append(", categoryRank=").append(categoryRank);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
