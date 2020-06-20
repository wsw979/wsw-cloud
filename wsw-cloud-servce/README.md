# 开发

## 1. 主从数据库

    参考任意一个
    mysql -> MYSQL.md
    https://blog.csdn.net/weixin_43832771/article/details/105866536
    
## 2. 执行sql文件

    mysql -> sql.sql

## 3. 由于是源码开发，需要本地install源码

    参考 alibaba-resources -> README.md
    
## 4. 模块规划
|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud-servce|wsw-cloud-nacos|注册中心模块|提供服务注册和配置文件|
|wsw-cloud-servce|wsw-cloud-sentinel|服务哨兵模块|提供服务动态降级、熔断配置|
|wsw-cloud-servce|wsw-cloud-auth|认证鉴权模块|提供认证、鉴权|
|wsw-cloud-servce|wsw-cloud-auth-api|接口调用模块|获取和效验认证|
|wsw-cloud-servce|wsw-cloud-auth-common|公共模块|提供认证、鉴权实现类|
|wsw-cloud-servce|wsw-cloud-gateway|网关|提供服务代理，请求拦截等功能|
|wsw-cloud-servce|wsw-cloud-gateway-api|网关动态路由|提供动态路由的增删查改|
|wsw-cloud-servce|wsw-cloud-gateway-service|网关核心模块|提供服务转发、限流、熔断、拦截功能|
|wsw-cloud-servce|wsw-cloud-gateway-common|网关公共模块|提供实现类|
|wsw-cloud-servce|wsw-cloud-common|公共模块|提供一系列初始化公共行为|
|wsw-cloud-servce|wsw-cloud-config|配置文件模块|提供配置能力|
|wsw-cloud-servce|wsw-cloud-data|通用属性模块|提供常用的工具类或者其他能力|
|wsw-cloud-servce|wsw-cloud-datasource|数据源模块|提供数据源配置|
|wsw-cloud-servce|wsw-cloud-exception|异常模块|提供异常类和返回类|
|wsw-cloud-servce|wsw-cloud-jar|架包模块|提供依赖服务架包管理|
|wsw-cloud-servce|wsw-cloud-log|日志模块|提供日志能力|
|wsw-cloud-servce|wsw-cloud-redis|缓存模块|提供NoSql缓存|
|wsw-cloud-servce|wsw-cloud-swagger|文档模块|提供文档|
