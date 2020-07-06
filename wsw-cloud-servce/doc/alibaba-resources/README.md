# 操作手册

## 0. 概述
    本地源码架包，方便开发调试。
## 1. 编译和启动
### 1.1 如何编译
    解压到本地目录，maven下载依赖完成，
    idea或mvn命令，选择root节点
    mvn clean install
    
## 2 nacos 1.2.1
    编译打包本地，install
    
## 3 sentinel 1.8.0
    编译打包本地，install -Dmaven.test.skip=true
    哨兵需要跳过单元测试
    本项目的哨兵控制台，重写了源码。
    可在控制台加入限流或熔断规则，根据spring.application.name-**-**存入nacos

## 4 seata 1.2.0
    BranchCommitRequestConvertor 源码在报错，有个类找不到
    
    直接打包，启动 servce
    mvn clean install -DskipTests=true
