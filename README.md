# 微头条
## 后端技术栈
- JAVA作为开发语言,版本为JDK13
- Tomcat作为服务容器,版本为10.1.19
- Mysql5.7.26用于项目存储数据
- Servlet用于控制层实现前后端数据交互
- JDBC用于实现数据的CURD
- Druid用于提供数据源的连接池
- MD5用于用户密码的加密
- Jwt用于token的生成和校验
- Jackson用于转换JSON
- Filter用于用户登录校验和跨域处理
## 主要功能 
1. 用户功能
  - 注册功能
  - 登录功能
2. 头条新闻
  - 新闻的分页浏览
  - 通过标题关键字搜索新闻
  - 查看新闻详情
  - 新闻的修改和删除
3. 权限控制
  - 用户只能修改和自己发布的头条新闻