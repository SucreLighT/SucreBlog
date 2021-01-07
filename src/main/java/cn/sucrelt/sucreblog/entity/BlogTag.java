package cn.sucrelt.sucreblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description: 标签实体类，对应数据库表tb_blog_tag
 * @author: sucre
 * @date: 2021/01/07
 * @time: 14:54
 */
public class BlogTag {

    /**
     * 标签id
     */
    @Getter
    @Setter
    private Integer tagId;

    /**
     * 标签名
     */
    @Getter
    @Setter
    private String tagName;

    /**
     * 逻辑删除标志
     */
    @Getter
    @Setter
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagId=").append(tagId);
        sb.append(", tagName=").append(tagName);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
