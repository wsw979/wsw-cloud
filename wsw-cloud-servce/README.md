# 开发

## 1. 主从数据库
    参考任意一个
    doc -> mysql -> MYSQL.md
    https://blog.csdn.net/weixin_43832771/article/details/105866536
    
## 2. 执行sql文件
    mysql -> sql.sql

## 3. 由于是源码开发，需要本地install源码
    参考 doc -> alibaba-resources -> README.md
    
## 4. 模块（按启动顺序）
    所有子模块，除非必要依赖，可以选择性依赖，比如job，没有定时任务不用依赖

###### 4.1 wsw-cloud-nacos
    提供注册和文件
    localhost:8848.
    
###### 4.2 wsw-cloud-job
    提供分布式任务调度，demo在gateway-api模块
    同时官网提供API动态调用。配置 accToken认证，由服务间动态调用（未做）。
    localhost:port
    
###### 4.3 wsw-cloud-sentinel
    提供服务熔断、降级策略，支持全局不写降级工厂，默认返回或抛出异常
    动态配置接口或资源熔断和降级策略
    localhost:port
    
###### 4.4 seata
    提供分布式事务管理
    解压doc -> seata 运行 bin -> .bat
    或
    解压alibaba-resources 下面源码启动 servce模块

###### 4.5 wsw-cloud-gateway-service
    提供网管服务。同时作为资源服务器。
    用户登录后，auth模块授权资源，gateway验证权限。
    动态路由

###### 4.6 wsw-cloud-auth
    提供登录，认证功能
    1.登录有账号/手机号/邮箱 密码/图形验证码
    2.手机号/邮箱 验证码登录
    3.二维码扫码（暂时没做，可以写前端页面拿到code就行）
    
    提供获取登录用户
    1.根据token获取
    2.调用user服务获取
    
    登录地址在 filter 的构造参数，网管调用

###### 4.7 wsw-cloud-user
    提供用户服务。包含APP-WX-WEB之类的前端用户 和 后台管理的admin用户
    
###### 4.8 wsw-cloud-gateway-api
    动态网管API，提供增删改查路由。
    实现实时修改网管路由，不用重启项目
    demo:
        job demo
        mq demo
        seata demo
    
###### 4.9 wsw-cloud-swagger
    提供文档，项目启动后，修改nacos配置中心swagger-config
    只有启动的项目和配置文件允许加载的项目才会显示。
    localhost:9999/doc.html
    