# Admin模块

## 1. 实体类与表结构

### 实体类[AdminUser](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/entity/AdminUser.java)

|   实体类AdminUser    | 数据库表tb_admin_user |             字段含义             |
| :------------------: | :-------------------: | :------------------------------: |
| Integer adminUserId  |     admin_user_id     |             管理员id             |
| String loginUserName |    login_user_name    |          管理员登录名称          |
| String loginPassword |    login_password     |         管理员的登录密码         |
|   String nickName    |       nick_name       |            管理员昵称            |
|     Byte locked      |        locked         | 是否锁定 0未锁定 1已锁定无法登陆 |

### 数据库表定义

```sql
CREATE TABLE `tb_admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT,
  `login_user_name` varchar(50) NOT NULL COMMENT,
  `login_password` varchar(50) NOT NULL COMMENT,
  `nick_name` varchar(50) NOT NULL COMMENT,
  `locked` tinyint(4) DEFAULT '0' COMMENT,
  PRIMARY KEY (`admin_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `tb_admin_user`(`admin_user_id`,`login_user_name`,`login_password`,`nick_name`,`locked`) values (1,'admin','123','sucre',0);
```

### 模型架构

|  三层架构  |        类        |
| :--------: | :--------------: |
|    Dao     | AdminUserMapper  |
|  Service   | AdminUserService |
| Controller | AdminController  |



## 2. 主要方法

[**AdminUserMapper**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/dao/AdminUserMapper.java)

|                             方法                             |                             功能                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| AdminUser login(@Param("userName") String userName, @Param("password") String password) | 用户登录方法：数据库中根据用户名和密码在tb_admin_user表中查询该用户，查询到则登录成功，否则登录失败。 |
|          AdminUser selectById(Integer adminUserId)           | 根据id查询用户：数据库中根据主键用户id进行查询，查询到则返回用户对象信息。 |
|         int updateByIdSelective(AdminUser adminUser)         | 根据id检索并条件更新用户信息：数据库中采取选择性更新的操作，根据参数中用户对象的id值查询到对象记录并更新。 |

[**AdminUserService**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/service/AdminUserService.java)

|                             方法                             |                             功能                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|      AdminUser login(String userName, String password)       |    传入参数用户名和密码，调用Dao中的方法实现用户的登录。     |
|          AdminUser getUserById(Integer loginUserId)          | 传入参数用户id，调用Dao中的方法查询数据库中是否存在该用户。  |
| Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) | 传入参数用户id，原始密码以及新密码，首先根据id在数据库中查询用户，如果未能查询到该用户直接返回false；查询到后比较新旧密码是否相同，不相同则调Dao中的条件更新方法更新密码。 |
| Boolean updateUserName(Integer loginUserId, String loginUserName, String nickName) |   与上一个更新密码的方法类似，该方法用于更新用户名和昵称。   |

[**AdminController**](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/controller/admin/AdminController.java)

|                             方法                             |                             功能                             |            请求路径            |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------: |
|                            index                             | 请求首页方法：request中设置path属性为index，同时返回字符串"admin/index"，指示控制器跳转到首页界面。 | GET：空，/，index，/index.html |
|                          无参login                           |          请求登录界面方法：直接返回"admin/login"。           |          GET：/login           |
| login(@RequestParam("userName") String userName,                     @RequestParam("password") String password,                     @RequestParam("verifyCode") String verifyCode,                     HttpSession session) | 用户登录方法：首先对前端传入的参数，包括用户名、密码和验证码进行校验。数据合法 后调用Service的方法进行登录验证，登录成功则在session中设置当前登录的用户信息并跳转到首页，否则返回登陆失败并跳转到登录界面。 |          POST：/login          |
|                           profile                            | 请求个人信息界面方法：根据session中存储的已登录的用户信息，调用Service方法查询对应的用户对象，将对象设置到request域中后跳转到profile页面。 |         GET：/profile          |
| updatePassword(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,                              @RequestParam("newPassword") String newPassword) |                      更新用户密码的方法                      |    POST：/profile/password     |
| updateUserName(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,                              @RequestParam("nickName") String nickName) |                    更新用户名和昵称的方法                    |      POST：/profile/name       |
|                            logout                            |      用户登出方法：清除request域中的相关用户信息即可。       |          GET：/logout          |

---

# 登录拦截功能

**拦截器作用：** 在浏览器通过url请求后台相关方法时，首先需要拦截器拦截，并进行校验，判断当前用户状态是否已经登录，只有登录后才可以进行相关的管理操作，如果当前处于未登录的状态，则拦截器会进行拦截并返回将前端界面跳转到登录界面，提示用户先登录。

## 主要类和方法

|                              类                              |      方法       |                             作用                             |
| :----------------------------------------------------------: | :-------------: | :----------------------------------------------------------: |
| [AdminLoginInterceptor](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/interceptor/AdminLoginInterceptor.java) |    preHandle    | 该类实现了Spring中内置的**HandlerInterceptor**接口，用于实现拦截器的具体功能。<br />其中包括preHandle、postHandle和afterCompletion三个，本功能中重写preHandle方法，表示拦截器在业务请求处理前进行的拦截功能。<br />首先获取当前session中的loginUser对应的值，如果存在则进行放行，否则跳转到登录界面提示用户先登录。 |
| [AdminLoginWebMvcConfigurer](https://github.com/tangtangsama/SucreBlog/blob/master/src/main/java/cn/sucrelt/sucreblog/config/AdminLoginWebMvcConfigurer.java) | addInterceptors | 该类实现Spring内置的**WebMvcConfigurer**接口，重写其中的addInterceptors方法，用于添加拦截器并设置拦截的url路径。 |

