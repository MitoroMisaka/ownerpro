# PaperManager Technical Document

## 前端

前端技术说明：



源码目录说明：



## 后端

1.技术点

2.项目结构

![image-20220619111955646](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\image-20220619111955646.png)

### 1.技术点

| 技术名称   | 功能                             | 备注                              |
| ---------- | -------------------------------- | --------------------------------- |
| SpringBoot | 后台开发框架                     |                                   |
| Maven      | 项目管理工具                     |                                   |
| Mybatis    | ORM  半自动化持久层框架          | 基于注解开发                      |
| Mysql      | 关系型数据库管理系统             |                                   |
| Redis      | 作为数据缓存使用                 | 可持久化的日志型、Key-Value数据库 |
| Md5        | 加密算法，用于用户密码的双向加密 |                                   |

### 2.项目结构

### Common

#### 封装常量

| 类名                     | 功能                                        | 备注 |
| ------------------------ | ------------------------------------------- | ---- |
| CommonConstants          | 保存项目中用到的所有常量                    |      |
| CommonErrorCode          | 枚举错误码                                  |      |
| CommomException          | 统一异常类型                                |      |
| `GlobalExceptionHandler` | 统一异常处理                                |      |
| `Page`                   | 对于有分页需要的数据，使  用 Page  进行封装 |      |
| `PageParam`              | 分页参数                                    |      |
| `Result`                 | 统一返回实体                                |      |

### Config

#### 各类插件设置

| 类名           | 功能                 | 备注       |
| -------------- | -------------------- | ---------- |
| CorsConfig     | 跨域资源共享         |            |
| Swagger2Config | 接口文档配置         | 分模块配置 |
| ShiroConfig    | Apache Shiro相关配置 |            |
| JsonConfig     | Gson相关配置         |            |

### Controller

#### 控制层接口

| AccountController    | 账户相关接口 | 登录注册，修改密码等             |
| -------------------- | ------------ | -------------------------------- |
| AreaController       | 领域相关接口 | 解决领域树形结构接口             |
| ArticleController    | 文章相关接口 | 包含文章的CRUD，作者等树形的操作 |
| CommentController    | 评论相关接口 | 树形评论                         |
| FileController       | 文章相关接口 | 文章上传，下载                   |
| Request              | 前端请求类   |                                  |
| Response             | 后端返回类   |                                  |
| SearchController     | 搜索相关接口 |                                  |
| StatisticsController | 统计所用接口 |                                  |

### DTO

后端功能实现中用到的特殊entity

| UserDTO     | 用户登录实现类 |      |
| ----------- | -------------- | ---- |
| UserComment | 用户评论类     |      |
| SessionData | 会话实体类     |      |

### Entity

#### 实体类

| Admin            | 管理员             |      |
| ---------------- | ------------------ | ---- |
| Area             | 领域               |      |
| Article          | 文章               |      |
| ArticleType      | 文章类型关联表类   |      |
| ArticleReference | 文章引用关联表类   |      |
| ArticleKeyword   | 文章关键词关联表类 |      |
| ArticleArea      | 文章领域关联表类   |      |
| Comment          | 评论类             |      |
| Files            | 文件类             |      |
| Keyword          | 关键词类           |      |
| Note             | 笔记类             |      |
| Reference        | 评论类             |      |
| Type             | 类型               |      |
| User             | 用户               |      |
| Writer           | 作者               |      |

### Mapper

通用mapper维护数据映射，仅列举部分

| AdminMapper   | 管理员mapper |      |
| ------------- | ------------ | ---- |
| UserMapper    | 用户mapper   |      |
| ArticleMapper | 文章Mapper   |      |
| ......        |              |      |

### Service

#### 服务层，维护服务逻辑代码

| Service     | 为Controller提供接口 |                  |
| ----------- | -------------------- | ---------------- |
| ServiceImpl | 实现Service层接口    | 根据功能区分模块 |

### Util

#### 工具类

| AssertUtil   | 处理断言                   |                  |
| ------------ | -------------------------- | ---------------- |
| DatesUtil    | 日期工具类                 |                  |
| MailUtil     | 邮件内容编辑工具           |                  |
| PasswordUtil | 密码工具类                 |                  |
| RedisUtil    | 封装基本的 redis  操作     |                  |
| RegexUtil    | 维护项目中需要的正则表达式 | 一般用以参数校验 |
| SessionUtils | 处理登录                   |                  |
| TimeUtil     | 时间工具类                 |                  |

### Xss

#### 防止脚本语言攻击

| XssFilter | 脚本语言过滤器 |      |
| --------- | -------------- | ---- |

