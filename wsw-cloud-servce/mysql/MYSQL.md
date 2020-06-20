# 主从教程

### 1. 解压 mysql.zip 文件两份，任意目录 ，本文使用版本8.0.20

### 2. 两份mysql根目录新建my.ini文件

### 3. 选择任意my.ini作为主库，加入以下配置
    [mysqld]
    #自定义
    server-id=8001
    #设置自定义端口
    port=8001
    #设置mysql的安装目录
    basedir=D:\JAVA\MySql80
    #设置mysql数据库的数据的存放目录
    datadir=D:\JAVA\MySql80\data
    #允许最大连接数
    max_connections=200
    #允许连接失败的次数。
    max_connect_errors=10
    #服务端使用的字符集默认为UTF8
    character-set-server=utf8
    #创建新表时将使用的默认存储引擎
    default-storage-engine=INNODB
    #默认使用“mysql_native_password”插件认证
    #mysql_native_password
    default_authentication_plugin=caching_sha2_password
    #从库会基于此log-bin来做复制
    log-bin=mysql-bin
    #不用于读写分离的具体数据库
    binlog_ignore_db=mysql
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=information_schema
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=sys
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=preformance_schema
    #选择row模式 
    binlog-format=ROW
    [mysql]
    #设置mysql客户端默认字符集
    default-character-set=utf8
    [client]
    #设置mysql客户端连接服务端时默认使用的端口
    port=8001
    default-character-set=utf8
    
### 4. 选择另外一个my.ini作为从库，加入以下配置
    [mysqld]
    #自定义
    server-id=8002
    #设置自定义端口
    port=8002
    #设置mysql的安装目录
    basedir=D:\JAVA\MySql802
    #设置mysql数据库的数据的存放目录
    datadir=D:\JAVA\MySql802\data
    #允许最大连接数
    max_connections=200
    #允许连接失败的次数。
    max_connect_errors=10
    #服务端使用的字符集默认为UTF8
    character-set-server=utf8
    #创建新表时将使用的默认存储引擎
    default-storage-engine=INNODB
    #默认使用“mysql_native_password”插件认证
    #mysql_native_password
    default_authentication_plugin=caching_sha2_password
    #从库会基于此log-bin来做复制
    log-bin=mysql-bin
    #不用于读写分离的具体数据库
    binlog_ignore_db=mysql
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=information_schema
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=sys
    #和binlog-do-db一样，可以设置多个
    binlog_ignore_db=preformance_schema
    #选择row模式 
    binlog-format=ROW
    [mysql]
    #设置mysql客户端默认字符集
    default-character-set=utf8
    [client]
    #设置mysql客户端连接服务端时默认使用的端口
    port=8002
    default-character-set=utf8
    
### 5. 初始化两个数据库 
    cd mysql/bin
    mysqld --initialize --console
    找到有password一行，复制root@localhost: 后面的初始密码
    
### 6. 安装mysql服务，MySql80服务名称自定义
    cd mysql/bin
    mysqld --install "MySql80" --defaults-file="D:/JAVA/\MySql80/my.ini"
    
    显示Service successFully installed 代表安装成功
### 7. 启动服务
    cd mysql/bin
    net start MySql80
    
### 8. 配置主从

##### 8.1 主库操作
    创建用户，从库连接时分配的角色
    create user '名字'@'%' identified WITH caching_sha2_password by '密码';
    创建用户权限
    grant replication slave on *.* to '名字'@'%';
    刷新权限
    flush privileges;
    查看节点
    show master status;
    保留主库cmd窗口

##### 8.2 从库操作
    设置主节点参数，指定主库端口，可能不是默认3306.， 指定加密方式 get_****_key = 1
    CHANGE MASTER TO MASTER_HOST='localhost', MASTER_USER='slave.979', MASTER_PASSWORD='root', MASTER_PORT=8001, MASTER_LOG_FILE='mysql-bin.000003', MASTER_LOG_POS=3089, get_master_public_key=1;
    启动主从复制
    start slave;
    查看主从同步状态
    show slave status\G;
    
    成功状态，两个yes 代表成功
    Slave_IO_Running: YES
    Slave_SQL_Running: YES
   
    如果一个为no，停止主从复制，百度
    stop slave;
    