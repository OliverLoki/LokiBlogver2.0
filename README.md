BlogVersion2

一个基于Springboot+Vue2.x+Jwt+ElementUI的多用户前后端分离博客系统

Version3正在搭建中

> 部分截图

![image-20220328193524131](https://s2.loli.net/2022/03/28/1QsoRku2CpEc9fJ.png)

![image-20220328193457267](https://s2.loli.net/2022/03/28/zR8nwpaDdSIL5kh.png)

![image-20220328193103498](https://s2.loli.net/2022/03/28/n1dCxei7obSsfgc.png)

> 技术选择

+ 从零开始搭建一个博客项目骨架，最好选择合适，熟悉的技术，并且在未来易拓展，适合微服务化体系等。所以一般以Springboot作为我们的框架基础

+ 数据层，我们常用的是Mybatis，易上手，方便维护。但是单表操作比较困难，特别是添加字段或减少字段的时候，比较繁琐，所以这里我推荐使用`Mybatis Plus`，为简化开发而生，只需简单配置，即可快速进行 CRUD 操作，从而节省大量时间。

+ 目前前后端分离的情况下，最佳实践的确应该是用token做会话保持

  Jwt是生成Token一种比较好的解决方案,在前后端分离项目中，避免了使用session，通过把用户数据（不包括敏感数据）放在请求头header中传给后端，既可以认证又可以授权（把角色放入token中），同时又由于签名的加密，无法伪造token，保证了安全性。


> 功能模块图

![image-20211019220029609](D:\桌面\P_picture_cahe\image-20211019220029609.png)

> 开发工具与开发环境

MySQL 8.0.23

前端

| 轮子                                                         | 版本            |
| ------------------------------------------------------------ | --------------- |
| Vue                                                          | @vue/cli 4.5.14 |
| ElementUI                                                    | 基于vue 2.x     |
| [typo.css](https://github.com/sofish/typo.css)               | V2.1.2          |
| [vue-element-admin](https://panjiachen.github.io/vue-element-admin-site/zh/) | V4.4.0          |
| [Editormd](https://pandao.github.io/editor.md/)开源在线 Markdown 编辑器 |                 |
| animial.css                                                  |                 |

后端

| 依赖                 | 版本   |
| -------------------- | ------ |
| Springboot           | v2.5.5 |
| Mybatis-plus         |        |
| ElasticSearch        |        |
| Jwt                  |        |
| Lombok               |        |
| Hibernate validatior |        |
| Redis                |        |

**开发工具与环境**

+ IDEA

+ jdk11
+ Mysql 8.0.23
+ Maven

## 数据库设计

使用在线网站`SQLDBM`做数据库设计

> [在线数据库设计](https://sqldbm.com/)

参考以下数据库设计规范

> [MySQL数据库设计规范](https://github.com/jly8866/archer/blob/master/src/docs/mysql_db_design_guide.md)

### **数据表**

- 博客数据表：blog

  对gmt字段的解释：在代码层面实现创建和更新时间的自动更新

  ![image-20211019230154311](https://s2.loli.net/2022/03/28/4xd3KjGO5SQDtua.png)

- 分类数据表：type

  ![image-20211019230214236](https://s2.loli.net/2022/03/28/R7SNYn6dc1OfeaL.png)

- 用户数据表：user

  ![image-20211019230240146](https://s2.loli.net/2022/03/28/dVDzQjO1Z5rslvh.png)

- 评论数据表：comment

  ![image-20211019230511252](https://s2.loli.net/2022/03/28/TFe86xONWfEmA2V.png)

- 标签数据表： tags

  ![image-20211019225133878](https://s2.loli.net/2022/03/28/CQFWrZqpKzUaMcY.png)

+ 标签博客数据表

  多对多关系需要单独建表	

  | 名      | 类型   |
  | ------- | ------ |
  | blog_id | bigint |
  | tag_id  | bogint |

  

- 相册数据表：picture

  ![image-20211019225054632](https://s2.loli.net/2022/03/28/m9VBrY3ob6eaFhf.png)

### **实体间关系**

博客和分类是多对一的关系：一个博客对应一个分类，一个分类可以对应多个博客
博客和用户是多对一的关系：一个博客对应一个用户，一个用户可以对应多个博客
博客和评论是一对多的关系：一个博客可以对应多个评论，一个评论对应一个博客

博客和标签是多对多的关系

相册表与其他表没有关联，任何人都可以上传，经过审核后可以展示



![image-20211018134254356](https://s2.loli.net/2022/03/28/1mc9u7WZXOlMGkd.png)



> 登录功能

SpringBoot+JWT+Vue+Axious+Vuex实现前后端分离下的身份登陆权限验证，思路如下

1. 客户端通过用户名和密码向服务器发送请求登陆
2. 服务器收到请求数据，在数据库进行查询验证
3. 如果验证成功，服务器签发一个Token给客户端
4. 客户端可以将Token存放到LocalStorage或者Cookie里
5. 客服端设置监听，每次跳转路由，就判断 LocalStroage 中有无 Token ，没有就跳转到登录页面，有则跳转到对应路由页面
6. 在Axios每次调后端接口，都要在请求头中加Token
7. 在后端设置拦截器，用户登录后的每次请求都会经过这个拦截器校验Token是否有效
8. 如果验证成功，则继续执行请求，返回请求到的数据







​	