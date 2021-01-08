# TODO：待完善

# 前言

基于SpringBoot搭建的个人博客网站项目。

+ 代码行数：

  > 统计后端的代码行数，不包括前端静态文件、模板以及数据库脚本。

+ 整体功能如下图：

  :scroll: **TODO：** 此处一个思维导图（参考艿艿的商城项目）





# 演示

## 登录界面

## 主界面

## TODO





# 技术



## 项目结构

|   模块   |                             功能                             |
| :------: | :----------------------------------------------------------: |
|  admin   |        用户管理，包括登录、修改用户名和密码、登出等。        |
| category |       分类模块，用于设置博客的分类，进行分类的增删改。       |
|   tag    | 标签模块，用于设置博客的标签，进行标签的增删改。<br />**标签与分类的区别：**标签类似于关键字，跟博客是多对多的关系，相较于分类与博客的一对多关系更为复杂。 |
|          |                                                              |
|          |                                                              |

------

后端项目目前的项目结构如下：

> [-] java
>
> ​	├──[-] cn.sucrelt.sucreblog
>
> ​		├──[-] config
>
> ​		├──[-] controller
>
> ​		├──[-] dao
>
> ​		├──[-] entity
>
> ​		├──[-] interceptor
>
> ​		├──[-] service
>
> ​		├──[-] util
>
> ​		SucreBlogAppilication.java
>
> [-] resources
>
> ​    ├──[-] mapper
>
> ​	├──[-] static
>
> ​	├──[-] template
>
> ​	application.yml



## 技术栈

### 后端

| 框架                                                      | 说明           | 版本 |
| --------------------------------------------------------- | -------------- | ---- |
| [Spring Boot](https://spring.io/projects/spring-boot)     | 应用开发框架   |      |
| [MySQL](https://www.mysql.com/cn/)                        | 数据库服务器   |      |
| [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html) | 数据持久层框架 |      |
| lombok                                                    |                |      |
| kaptcha                                                   |                |      |

### 前端

| 框架      | 说明 | 版本 |
| --------- | ---- | ---- |
| Thymeleaf | 框架 |      |



# 模块开发文档

+ 数据库建立及环境搭建
+ [用户管理模块](https://github.com/tangtangsama/SucreBlog/blob/master/docs/admin.md)
+ [分类模块](https://github.com/tangtangsama/SucreBlog/blob/master/docs/category.md)
+ [标签模块](https://github.com/tangtangsama/SucreBlog/blob/master/docs/tag.md)



# 项目开发进度

|    状态:hammer:    |     模块:books:     |                 日期 :calendar:                 |
| :----------------: | :-----------------: | :---------------------------------------------: |
| :heavy_check_mark: |  admin用户管理模块  | :two::zero::two::zero:-:one::two:-:three::zero: |
| :heavy_check_mark: |  category分类模块   |  :two::zero::two::one:-:zero::one:-:zero::six:  |
| :heavy_check_mark: |     tag标签模块     | :two::zero::two::one:-:zero::one:-:zero::seven: |
|        :x:         |  blog博客管理模块   |                                                 |
|                    | comment评论管理模块 |                                                 |



# 其它

+ [开发过程问题](https://github.com/tangtangsama/SucreBlog/blob/master/docs/problems.md)

