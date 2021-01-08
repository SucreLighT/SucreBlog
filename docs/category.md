# Category模块

## 1. 实体类与表结构

### 实体类[BlogCategory](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/entity/BlogCategory.java)

|  实体类BlogCategory  | 数据库表tb_blog_category |               字段含义                |
| :------------------: | :----------------------: | :-----------------------------------: |
|  Integer categoryId  |       category_id        |            分类表id，主键             |
| String categoryName  |      category_name       |              分类的名称               |
| String categoryIcon  |      category_icon       |              分类的图标               |
| Integer categoryRank |      category_rank       |  分类的排序值，数值越大表示用的越多   |
|    Byte isDeleted    |        is_deleted        | 是否删除，分类逻辑删除标志，1表示删除 |
|   Date createTime    |       create_time        |               创建时间                |

### 数据库表定义

```sql
CREATE TABLE `tb_blog_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) NOT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) NOT NULL COMMENT '分类的图标',
  `category_rank` int(11) NOT NULL DEFAULT '1' COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 模型架构

|  三层架构  |         类         |
| :--------: | :----------------: |
|    Dao     |   CategoryMapper   |
|  Service   |  CategoryService   |
| Controller | CategoryController |

---



## 2. 主要方法

[**CategoryMapper**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/dao/CategoryMapper.java)

|                             方法                             |             功能             |
| :----------------------------------------------------------: | :--------------------------: |
| List<BlogCategory> **getCategoryList**(PageQueryUtil PageQueryUtil) |      获取分类的分页数据      |
|   int **getTotalCategories**(PageQueryUtil PageQueryUtil)    |       获取所有的分类数       |
|   BlogCategory **selectByCategoryId**(Integer categoryId)    | 根据分类id查询对应的分类对象 |
|  BlogCategory **selectByCategoryName**(String categoryName)  | 根据分类名查询对应的分类对象 |
|      int **insertSelective**(BlogCategory blogCategory)      |     条件插入分类对象数据     |
| int **updateByCategoryIdSelective**(BlogCategory blogCategory) |  根据分类id条件更新分类数据  |
|           int **deleteCategories**(Integer[] ids)            |       批量删除分类数据       |

[**CategoryService**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/service/CategoryService.java)

|                             方法                             |                             功能                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| public PageResult **getBlogCategoryPage**(PageQueryUtil PageQueryUtil) | 获取分类的分页数据：调用dao层的getCategoryList和getTotalCategories方法，将获取到的分页数据和总页数作为参数，封装到PageResult对象中并返回该对象。 |
|             public int **getTotalCategories**()              |  获取所有的分页数：调用dao层的getTotalCategories方法即可。   |
| public Boolean **addCategory**(String categoryName, String categoryIcon) | 添加一条分类数据：首先调用dao中的根据分类名查询分类的方法，判断是否已经存在该分类，如果不存在则新建分类对象并调用dao中的insertSelective方法进行添加，否则返回false。 |
| public Boolean **updateCategory**(Integer categoryId, String categoryName, String categoryIcon) | 更新一条分类数据：首先调用dao中的根据分类名查询分类的方法，判断是否已经存在该分类，如果不存在则新建分类对象并调用dao中的updateByCategoryIdSelective方法进行更新，否则返回false。 |
|      public Boolean **deleteCategories**(Integer[] ids)      | 批量删除分类数据：首先判断参数ids是否合理，然后调用dao中的deleteCategories方法进行删除。 |

[**CategoryController**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/controller/admin/CategoryController.java)

|                             方法                             |                             功能                             |         请求路径         |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------: |
|  public String **categoryPage**(HttpServletRequest request)  |         请求分类界面方法：直接返回"admin/category"。         |     GET：/categories     |
| public Result **list**(@RequestParam Map<String, Object> params) | 获取分类列表，根据前端传的分页参数进行查询并返回分页数据以供前端页面进行数据渲染。 |  GET：/categories/list   |
| public Result **addCategory**(@RequestParam("categoryName") String categoryName, @RequestParam("categoryIcon") String categoryIcon) | 首先对参数进行校验，之后交给业务层代码进行操作，根据名称查询是否已经存在该分类，之后才会进行数据封装并进行数据库insert操作。 |  POST：/categories/save  |
| public Result **updateCategory**(@RequestParam("categoryId") Integer categoryId, @RequestParam("categoryName") String categoryName,                              @RequestParam("categoryIcon") String categoryIcon) | 首先对参数进行校验，之后交给业务层代码进行操作，根据名称查询是否已经存在该分类，之后才会进行数据封装并进行数据库update操作。 | POST：/categories/update |
| public Result **deleteCategories**(@RequestBody Integer[] ids) | 将前端传过来的参数封装为 id 数组，参数验证通过后则调用批量删除方法进行数据库操作，否则将向前端返回错误信息。 | POST：/categories/delete |

