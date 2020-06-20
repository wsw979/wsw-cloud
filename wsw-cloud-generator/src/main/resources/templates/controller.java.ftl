package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.xuhong.blogs.core.component.RetJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<#if restControllerStyle>
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>


import javax.validation.Valid;
import java.util.List;

/**
* <p>
    * ${table.comment} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Api(tags = "${table.comment}")
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation(value = "${table.comment}分页列表", response = ${entity}.class)
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "页面", dataType = "Long"),
    @ApiImplicitParam(name = "size", value = "页面数据量", dataType = "Long"),
    @ApiImplicitParam(name = "sort", value = "排序方式排序[true:正序; false:倒序]", dataType = "Boolean"),
    @ApiImplicitParam(name = "sortName", value = "排序字段,参照返回字段", dataType = "String")})
    @PostMapping(value = "/page")
    public  Object list(@Valid @RequestBody ${entity} param) {

    Object data = ${table.serviceName?uncap_first}.page(param);
    return RetJson.ok(data);
    }

    @ApiOperation(value = "${table.comment}详情", response = ${entity}.class)
    @GetMapping(value = "/info/{id}")
    public  Object info(@PathVariable Long id) {

    Object data = ${table.serviceName?uncap_first}.info(id);
    return RetJson.ok(data);
    }

    @ApiOperation(value = "${table.comment}新增")
    @PostMapping(value = "/add")
    public  Object add(@Valid @RequestBody ${entity} param) {

    ${table.serviceName?uncap_first}.add(param);
    return RetJson.ok();
    }

    @ApiOperation(value = "${table.comment}修改")
    @PostMapping(value = "/modify")
    public  Object modify(@Valid @RequestBody ${entity} param) {

    ${table.serviceName?uncap_first}.modify(param);
    return RetJson.ok();
    }

    @ApiOperation(value = "${table.comment}删除(单个条目)")
    @GetMapping(value = "/remove/{id}")
    public  Object remove(@PathVariable Long id) {

    ${table.serviceName?uncap_first}.remove(id);
    return RetJson.ok();
    }

    @ApiOperation(value = "${table.comment}删除(多个条目)")
    @PostMapping(value = "/removes")
    public  Object removes(@Valid @RequestBody List<Long> ids) {

    ${table.serviceName?uncap_first}.removes(ids);
    return RetJson.ok();
    }

    }
</#if>