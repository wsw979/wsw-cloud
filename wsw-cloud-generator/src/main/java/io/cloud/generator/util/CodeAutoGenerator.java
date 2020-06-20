package io.cloud.generator.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: wsw-cloud-generator
 * @description:
 * @author: wsw
 * @create: 2020-05-03 14:19
 **/
@Slf4j
public class CodeAutoGenerator {


    // 作者
    private static final String author = "wsw";
    //全局路劲
    private static final String projectPath = "D:/JAVA/wsw-cloud/wsw-cloud-user";
    //entity 生成路劲
    private static final String apiPath = "/wsw-cloud-user-common/src/main/java/";
    //controller,service,impl,mapper 生成路劲
    private static final String bizPath = "/wsw-cloud-user-api/src/main/java/";
    //xml生成路劲
    private static final String xmlPath = "/wsw-cloud-user-api/src/main/resources/";
    // 实体类包名
    private static final String entityPackage = "io.cloud.user.common";
    // 其他类包名
    private static final String otherPackage = "io.cloud.user.api";

    // 数据库
    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:8001/wsw-cloud-config";
    private static DbType DB_TYPE = DbType.MYSQL;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    //表前缀
    private static final String[] tablePrefix = { "c_" };
    //需要生成的表
    private static final String[] tables =
            {
                    "c_api_message_code",
                    "c_api_role",
                    "c_api_user",
                    "c_api_user_auth",
                    "c_api_user_role"
            };
    //父类公共字段
    private static final String[] columns =
            {
                    "create_id","update_id","create_time","update_time"
            };


    /**
     * 以下配置无需更改
     */
    /** controller输出模板 */
    private static String CONTROLLER_TEMPLATE = "templates/controller.java.ftl";
    private static String CONTROLLER_OUTPUT_PATH = otherPackage + ".controller";
    /** service输出模板 */
    private static String SERVICE_TEMPLATE = "templates/service.java.ftl";
    private static String SERVICE_OUTPUT_PATH = otherPackage + ".service";
    /** serviceImpl输出模板 */
    private static String SERVICE_IMPL_TEMPLATE = "templates/serviceImpl.java.ftl";
    private static String SERVICE_IMPL_OUTPUT_PATH = otherPackage + ".service.impl";
    /** mapper.java输出模板 */
    private static String MAPPER_TEMPLATE = "templates/mapper.java.ftl";
    private static String MAPPER_OUTPUT_PATH = otherPackage + ".mapper";
    /** entity输出模板 */
    private static String ENTITY_TEMPLATE = "templates/entity.java.ftl";
    private static String ENTITY_OUTPUT_PATH = entityPackage + ".entity";
    /** mapper.xml输出模板 */
    private static String XML_TEMPLATE = "templates/mapper.xml.ftl";
    private static String XML_OUTPUT_PATH = projectPath + xmlPath + "/mapper/";

    public static void main(String[] args) {

        // 全局配置
        GlobalConfig globalConfig = globalConfig();
        // 数据源配置
        DataSourceConfig dataSourceConfig = dataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = strategyConfig();
        // 包配置
        PackageConfig packageConfig = packageConfig();
        // 自定义配置
        InjectionConfig injectionConfig = injectionConfig();
        // 模板配置
        TemplateConfig templateConfig = templateConfig();
        // 执行
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .execute();
    }

    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                // 打开文件
                .setOpen(false)
                // 文件覆盖
                .setFileOverride(true)
                // 开启activeRecord模式
                .setActiveRecord(false)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap: mapper.xml生成查询映射结果
                .setBaseResultMap(true)
                // XML ColumnList: mapper.xml生成查询结果列
                .setBaseColumnList(true)
                // swagger注解; 须添加swagger依赖
                .setSwagger2(true)
                // 作者
                .setAuthor(author)
                //时间类型
                .setDateType(DateType.TIME_PACK)
                //设置主键生成策略
                .setIdType(IdType.ID_WORKER)
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setServiceName("I%sService");
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(DB_TYPE)
                // 连接驱动
                .setDriverName(driverClassName)
                // 地址
                .setUrl(url + "?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC")
                // 用户名
                .setUsername(username)
                // 密码
                .setPassword(password);
    }

    /**
     * 策略配置
     */
    private static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                // 表名生成策略：下划线连转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表字段生成策略：下划线连转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(tables)
                // 生成controller
                .setRestControllerStyle(true)
                //序列化
                .setEntitySerialVersionUID(true)
                // 去除   表前缀
                .setTablePrefix(tablePrefix)
                // controller映射地址：驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // 是否启用builder 模式
                .setEntityBuilderModel(true)
                // 是否为lombok模型; 需要lombok依赖
                .setEntityLombokModel(true)
                // 自定义实体父类
                .setSuperEntityClass("io.cloud.data.base.BaseEntity")
                // 自定义父类公共字段
                .setSuperEntityColumns(columns);
    }

    /**
     * 包配置
     * 设置包路径用于导包时使用，路径示例：com.path
     */
    private static PackageConfig packageConfig() {

        String entity = ENTITY_OUTPUT_PATH;
        String mapper = MAPPER_OUTPUT_PATH;
        String xml = XML_OUTPUT_PATH;
        String service = SERVICE_IMPL_TEMPLATE;
        String serviceImpl = SERVICE_IMPL_OUTPUT_PATH;
        String controller = CONTROLLER_OUTPUT_PATH;

        return new PackageConfig()
                // 父包名
                .setParent("")
                .setEntity(ENTITY_OUTPUT_PATH)
                .setMapper(MAPPER_OUTPUT_PATH)
                .setXml(XML_OUTPUT_PATH)
                .setService(SERVICE_OUTPUT_PATH)
                .setServiceImpl(SERVICE_IMPL_OUTPUT_PATH)
                .setController(CONTROLLER_OUTPUT_PATH);
    }

    /**
     * 模板配置
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig()
                // 置空后方便使用自定义输出位置
                .setEntity(null)
                .setXml(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入配置

            }
        }
                // 判断是否创建文件
                .setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        // 检查文件目录，不存在自动递归创建
                        checkDir(filePath);
                        isExists(filePath);
                        return true;
                    }
                })
                // 自定义输出文件
                .setFileOutConfigList(fileOutConfigList());
    }

    /**
     * 自定义输出文件配置
     */
    private static List<FileOutConfig> fileOutConfigList() {
        /**
         * 路径拼接
         */
        String api = projectPath + apiPath;
        String biz = projectPath + bizPath;

        String entity = api + ENTITY_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String xml = XML_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String mapper = biz + MAPPER_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String service = biz + SERVICE_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String impl = biz + SERVICE_IMPL_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String controller = biz + CONTROLLER_OUTPUT_PATH.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);

        List<FileOutConfig> list = new ArrayList<>();
        // 实体类文件输出
        list.add(new FileOutConfig(ENTITY_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return entity + StringPool.SLASH  + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        // mapper xml文件输出
        list.add(new FileOutConfig(XML_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return xml + StringPool.SLASH + tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });
        // mapper文件输出
        list.add(new FileOutConfig(MAPPER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return mapper + StringPool.SLASH + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        // service文件输出
        list.add(new FileOutConfig(SERVICE_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return service + StringPool.SLASH + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        // service impl文件输出
        list.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return impl + StringPool.SLASH + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        // controller文件输出
        list.add(new FileOutConfig(CONTROLLER_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return controller + StringPool.SLASH + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });

        return list;
    }

    /**
     * 判断文件是否存在
     * @param path 路径
     * @return
     */
    private static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}

