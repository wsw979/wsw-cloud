## 1.模块
|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud|wsw-cloud-generator|代码生产|MybatisPlus生产代码模块|
|wsw-cloud|wsw-cloud-servce|中心服务|主要配置在此模块|
|wsw-cloud|wsw-cloud-user|用户模块|包含app和admin|

|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud-servce|wsw-cloud-nacos|注册中心模块|提供服务注册和配置文件|
|wsw-cloud-servce|wsw-cloud-sentinel|服务哨兵模块|提供服务动态降级、熔断配置|
|wsw-cloud-servce|wsw-cloud-job|分布式任务调度模块|提供分布式定时器的调度|

|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud-auth|wsw-cloud-auth-api|接口调用模块|获取和效验认证|
|wsw-cloud-auth|wsw-cloud-auth-common|公共模块|提供认证、鉴权实现类|

|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud-gateway|wsw-cloud-gateway-api|网关动态路由|提供动态路由的增删查改|
|wsw-cloud-gateway|wsw-cloud-gateway-service|网关核心模块|提供服务转发、限流、熔断、拦截功能|
|wsw-cloud-gateway|wsw-cloud-gateway-common|网关公共模块|提供实现类|

|项目|模块|名称|说明|
|---|---|---|---|
|wsw-cloud-common|wsw-cloud-core|配置文件模块|提供配置能力|
|wsw-cloud-common|wsw-cloud-data|通用属性模块|提供常用的工具类或者其他能力|
|wsw-cloud-common|wsw-cloud-datasource|数据源模块|提供数据源配置|
|wsw-cloud-common|wsw-cloud-exception|异常模块|提供异常类和返回类|
|wsw-cloud-common|wsw-cloud-jar|架包模块|提供依赖服务架包管理|
|wsw-cloud-common|wsw-cloud-log|日志模块|提供日志能力|
|wsw-cloud-common|wsw-cloud-mq|队列模块|提供消息队列|
|wsw-cloud-common|wsw-cloud-redis|缓存模块|提供NoSql缓存|
|wsw-cloud-common|wsw-cloud-seata|分布式事务模块|提供at模式，TCC未集成|
|wsw-cloud-common|wsw-cloud-sms|短信模块|提供阿里短信业务|
|wsw-cloud-common|wsw-cloud-swagger|文档模块|提供文档|
|wsw-cloud-common|wsw-cloud-es|es模块|提供日志的快速搜索（尚未集成）|

## 2.开发
nacos
sentinel （修改了源码，后台增删改配置文件，同步nacos持久化）
job
seata   （wsw-cloud-service -> doc -> .bat 可以启动）
源码开发,方便修改配置文件信息和打包
参考 wsw-cloud-servce -> README.md

## 3.demo
分布式事务
分布式定时任务调用
分布式队列
wsw-cloud-gateway-api 模块有示例