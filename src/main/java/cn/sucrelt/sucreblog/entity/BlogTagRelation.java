package cn.sucrelt.sucreblog.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description: 博客和标签关系的实体类，对应数据库中间表tb_blog_tag_relation
 * @author: sucre
 * @date: 2021/01/07
 * @time: 16:44
 */
public class BlogTagRelation {
    @Getter
    @Setter
    private Long relationId;

    @Getter
    @Setter
    private Long blogId;

    @Getter
    @Setter
    private Integer tagId;

    @Getter
    @Setter
    private Date createTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", relationId=").append(relationId);
        sb.append(", blogId=").append(blogId);
        sb.append(", tagId=").append(tagId);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
