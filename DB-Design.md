#### 最终的ER图：

![image-20220619120358892](C:\Users\Eyja\AppData\Roaming\Typora\typora-user-images\image-20220619120358892.png)



## 数据库表设计：

![image-20220619123556739](C:\Users\Eyja\AppData\Roaming\Typora\typora-user-images\image-20220619123556739.png)

### user  用户

| 字段名     | 字段类型     | 字段含义         | 备注                                   |
| ---------- | ------------ | ---------------- | -------------------------------------- |
| id         | bigint       | 主键             |                                        |
| name       | varchar(255) | 用户昵称         |                                        |
| username   | varchar(255) | 账号             |                                        |
| password   | varchar(255) | 密码             |                                        |
| type       | int          | 用户类型         |                                        |
| session_id | varchar(255) | 会话id           |                                        |
| avatar     | varchar(255) | 头像             | 存储的是图片的地址                     |
| select_set | int          | 查询的权限       | 1表示有权限，0表示无权限，需管理员授权 |
| update_set | int          | 修改数据的权限   | 同上                                   |
| insert_set | int          | 发布新内容的权限 | 同上                                   |
| delete_set | int          | 删除的权限       | 同上                                   |



### admin  管理员

| 字段名   | 字段类型     | 字段含义 | 备注               |
| -------- | ------------ | -------- | ------------------ |
| id       | bigint       | 主键     |                    |
| username | varchar(255) | 账号     |                    |
| password | varchar(255) | 密码     |                    |
| type     | int          | 用户类型 |                    |
| name     | varchar(255) | 用户昵称 |                    |
| avatar   | varchar(255) | 头像     | 存储的是图片的地址 |



### article  文章

| 字段名           | 字段类型     | 字段含义               | 备注 |
| ---------------- | ------------ | ---------------------- | ---- |
| article_id       | bigint       | 主键                   |      |
| title            | varchar(255) | 文章标题               |      |
| magazine         | varchar(255) | 发表这篇文章的期刊     |      |
| date             | datetime     | 发表时间               |      |
| abstract_content | text         | 文章摘要               |      |
| url              | varchar(255) | 文章链接或路径         |      |
| upload_time      | datetime     | 上传时间               |      |
| comment_num      | int          | 关于这篇文章的评论数量 |      |



### writer  作者

| 字段名    | 字段类型     | 字段含义     | 备注 |
| --------- | ------------ | ------------ | ---- |
| writer_id | bigint       | 主键         |      |
| name      | varchar(255) | 作者姓名     |      |
| email     | varchar(255) | 作者邮箱地址 |      |



### article_writer

| 字段名     | 字段类型 | 字段含义 | 备注                        |
| ---------- | -------- | -------- | --------------------------- |
| article_id | bigint   |          | 对应于article表的article_id |
| writer_id  | bigint   |          | 对应于writer表的writer_id   |



### reference  参考文献

| 字段名       | 字段类型     | 字段含义           | 备注 |
| ------------ | ------------ | ------------------ | ---- |
| reference_id | bigint       | 主键               |      |
| name         | varchar(255) | 参考文献名称       |      |
| url          | varchar(255) | 参考文献链接或路径 |      |



### article_reference

| 字段名       | 字段类型     | 字段含义       | 备注                            |
| ------------ | ------------ | -------------- | ------------------------------- |
| article_id   | bigint       |                | 对应于article表的article_id     |
| reference_id | bigint       |                | 对应于reference表的reference_id |
| note         | varchar(255) | 一些有关的备注 |                                 |



### type  文章类型

| 字段名  | 字段类型     | 字段含义   | 备注 |
| ------- | ------------ | ---------- | ---- |
| type_id | bigint       | 主键       |      |
| name    | varchar(255) | 类型的名称 |      |



### article_type

| 字段名     | 字段类型 | 字段含义 | 备注                        |
| ---------- | -------- | -------- | --------------------------- |
| article_id | bigint   |          | 对应于article表的article_id |
| type_id    | bigint   |          | 对应于type表的type_id       |



### keyword  关键字

| 字段名     | 字段类型     | 字段含义     | 备注 |
| ---------- | ------------ | ------------ | ---- |
| keyword_id | bigint       | 主键         |      |
| name       | varchar(255) | 关键字的名称 |      |



### article_keyword

| 字段名     | 字段类型 | 字段含义 | 备注                        |
| ---------- | -------- | -------- | --------------------------- |
| article_id | bigint   |          | 对应于article表的article_id |
| keyword_id | bigint   |          | 对应于keyword表的keyword_id |



### area  研究领域

| 字段名   | 字段类型     | 字段含义     | 备注                                                         |
| -------- | ------------ | ------------ | ------------------------------------------------------------ |
| area_id  | bigint       | 主键         |                                                              |
| name     | varchar(255) | 研究领域名称 |                                                              |
| super_id | bigint       | 父节点的id   | 如果处于根部，值为0（area_id从1开始递增，故0作为根节点的特殊值） |



### article_area

| 字段名     | 字段类型 | 字段含义 | 备注                        |
| ---------- | -------- | -------- | --------------------------- |
| article_id | bigint   |          | 对应于article表的article_id |
| article_id | bigint   |          | 对应于article表的article_id |



### file  附加上传的文件

| 字段名      | 字段类型     | 字段含义             | 备注                 |
| ----------- | ------------ | -------------------- | -------------------- |
| file_id     | bigint       | 主键                 |                      |
| article_id  | bigint       | 所属文章的id         |                      |
| file_path   | varchar(255) | 文件路径             |                      |
| file_name   | varchar(255) | 文件名               |                      |
| file_suffix | varchar(255) | 文件后缀（文件类型） | 决定了文件的打开方式 |



### note  文章笔记

| 字段名       | 字段类型     | 字段含义     | 备注                               |
| ------------ | ------------ | ------------ | ---------------------------------- |
| note_id      | bigint       | 主键         |                                    |
| article_id   | bigint       | 所属文章的id |                                    |
| content      | text         | 笔记内容     | text可通过Markdown渲染出图片等内容 |
| publisher    | varchar(255) | 发布者       |                                    |
| publish_time | datetime     | 发布时间     |                                    |
| likes        | int          | 点赞数       |                                    |



### comment  评论

| 字段名       | 字段类型     | 字段含义           | 备注                                                         |
| ------------ | ------------ | ------------------ | ------------------------------------------------------------ |
| comment_id   | bigint       | 主键               |                                                              |
| comment_time | datetime     | 评论时间           |                                                              |
| content      | varchar(255) | 评论内容           |                                                              |
| likes        | int          | 点赞数             |                                                              |
| id           | bigint       | 用户id             |                                                              |
| note_id      | bigint       | 评论针对的笔记的id |                                                              |
| super_id     | bigint       | 父节点的id         | 如果处于根部，值为0（area_id从1开始递增，故0作为根节点的特殊值） |
| name         | varchar(255) | 这条评论的用户名   |                                                              |
| to_user      | varchar(255) | 被回复的用户名     |                                                              |



### search_record  用户搜索记录

| 字段名     | 字段类型     | 字段含义   | 备注 |
| ---------- | ------------ | ---------- | ---- |
| histort_id | bigint       | 主键       |      |
| id         | bigint       | 用户id     |      |
| content    | varchar(255) | 搜索内容   |      |
| time       | datetime     | 搜索的时刻 |      |

