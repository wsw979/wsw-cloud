package ${package.Entity};

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.Date;
import com.deloitte.freport.annotation.QuotaCodeAnn
import com.deloitte.freport.enums.QuotaEnum;
<#assign xxx = ["mi_business_type_l3","mi_business_type_l2","sub_channel_l2","distribution_channel_l3","distribution_channel_l2","insurance_type_l3","insurance_type_l2","type","product_type","business_type_l3","business_type_l2","business_type_l4","higher_product_level","medical_insurance_type","business_type_l3","create_time","last_changed","settled_time","status", "org_type", "org","business_type_merge","insurance_type_l4","business_type_merge"]/>

<#list table.importPackages as pkg>
    import ${pkg};
</#list>
<#if entityLombokModel>

    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
</#if>

/**
* <p>
    * <#if table.comment??>${table.comment}</#if>
    * </p>
*
* @table ${table.name}
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
</#if>
<#if superEntityClass??>
    public class ${entity} implements ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
    public class ${entity} extends Model<${entity}> {
<#else>
    public class ${entity} implements Serializable {
</#if>

private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        /**
        * ${field.comment}
        */
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
            @TableId(value = "${field.name}", type = IdType.AUTO)
        <#elseif idType??>
            @TableId(value = "${field.name}", type = IdType.${idType})
        <#elseif field.convert>
            @TableId("${field.name}")
        </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
            @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
        <#else>
            @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
        @TableField("${field.name}")
    </#if>
<#-- 乐观锁注解 -->
    <#if versionFieldName!"" == field.name>
        @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if logicDeleteFieldName!"" == field.name>
        @TableLogic
    </#if>
    <#if xxx?seq_contains(field.name)>
        @Column(column = "${field.name}")
        private ${field.propertyType} ${field.propertyName};
    <#else>
        @Column(column = "${field.name}")
        @QuotaCodeAnn(codeEnum = QuotaEnum.${field.name})
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
        public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
        }

        <#if entityBuilderModel>
            public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
            public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
            return this;
        </#if>
        }
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
        public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return this.id;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
    "}";
    }
</#if>
}