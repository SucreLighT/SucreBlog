# Tag模块

## 1. 实体类与表结构

### 实体类[BlogTag](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/entity/BlogTag.java)

|  实体类BlogTag  | 数据库表tb_blog_tag |      字段含义      |
| :-------------: | :-----------------: | :----------------: |
|  Integer tagId  |       tag_id        |   标签表id，主键   |
| String tagName  |      tag_name       |      标签名称      |
| Byte isDeleted  |     is_deleted      | 是否删除 0=否 1=是 |
| Date createTime |     create_time     |      创建时间      |

**由于博客和标签为多对多的关系，因此需要一张中间表用来描述博客和标签的关系。**

### 实体类[BlogTagRelation](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/entity/BlogTagRelation.java)

| 实体类BlogTagRelation | 数据库表tb_blog_tag_relation |    字段含义    |
| :-------------------: | :--------------------------: | :------------: |
|    Long relationId    |         relation_id          | 关系表id，主键 |
|      Long blogId      |           blog_id            |     博客id     |
|     Integer tagId     |            tag_id            |     标签id     |
|    Date createTime    |         create_time          |    创建时间    |

### 数据库表定义

```sql
DROP TABLE IF EXISTS `tb_blog_tag`;
CREATE TABLE `tb_blog_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) NOT NULL COMMENT '标签名称',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_blog_tag_relation`;
CREATE TABLE `tb_blog_tag_relation` (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 模型架构

|  三层架构  |                  类                  |
| :--------: | :----------------------------------: |
|    Dao     | BlogTagMapper、BlogTagRelationMapper |
|  Service   |              TagService              |
| Controller |            TagController             |

---



## 2. 主要方法

[**BlogTagMapper**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/dao/BlogTagMapper.java)

|                           方法                            |             功能             |
| :-------------------------------------------------------: | :--------------------------: |
| List<BlogTag> **getTagList**(PageQueryUtil pageQueryUtil) |      获取标签的分页数据      |
|     int **getTotalTags**(PageQueryUtil pageQueryUtil)     |       获取所有的标签数       |
|        BlogTag **selectByTagName**(String tagName)        | 根据标签名查询对应的标签对象 |
|         int **insertSelective**(BlogTag blogTag)          |     条件插入标签对象数据     |
|             int **deleteTags**(Integer[] ids)             |       批量删除标签数据       |

[**BlogTagRelationMapper**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/dao/BlogTagRelationMapper.java)

|                         方法                          |                功能                |
| :---------------------------------------------------: | :--------------------------------: |
| List<Long> **selectDistinctTagIds**(Integer[] tagIds) | 根据标签id查询关系表中是否存在数据 |

[**TagService**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/service/TagService.java)

|                             方法                             |                             功能                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| public PageResult **getBlogTagPage**(PageQueryUtil pageQueryUtil) | 获取标签的分页数据：调用dao层的getTagList和getTotalTags方法，将获取到的分页数据和总页数作为参数，封装到PageResult对象中并返回该对象。 |
|              Boolean **addTag**(String tagName)              | 添加一条标签数据：首先调用dao中的根据标签名查询标签的方法，判断是否已经存在该标签，如果不存在则新建标签对象并调用dao中的insertSelective方法进行添加，否则返回false。 |
|            Boolean **deleteTags**(Integer[] ids)             | 批量删除标签数据：首先调用BlogTagRelationMapper中的**selectDistinctTagIds**方法判断该标签在中间表中是否有关联的博客，如果没有则调用dao中的deleteTags方法进行删除，否则返回false表示该标签无法删除。 |

[**TagController**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/controller/admin/TagController.java)

|                             方法                             |                             功能                             |      请求路径      |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------: |
|    public String **tagPage**(HttpServletRequest request)     |           请求标签界面方法：直接返回"admin/tag"。            |     GET：/tags     |
| public Result **list**(@RequestParam Map<String, Object> params) | 获取标签列表：接收前端传来的分页参数，如 page 、limit 等参数，之后将数据总数和对应页面的数据列表查询出来并封装为分页数据返回给前端。 |  GET：/tags/list   |
| public Result **addTag**(@RequestParam("tagName") String tagName) | 添加标签：首先对参数进行校验，之后交给业务层代码进行操作，根据名称查询是否已经存在该标签，之后才会进行数据封装并进行数据库insert操作。 |  POST：/tags/save  |
|     public Result **delete**(@RequestBody Integer[] ids)     | 将前端传过来的参数封装为 id 数组，参数验证通过后则调用批量删除方法进行数据库操作，否则将向前端返回错误信息。 | POST：/tags/delete |

