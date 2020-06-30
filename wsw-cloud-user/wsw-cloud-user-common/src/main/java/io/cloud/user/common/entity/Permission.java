package io.cloud.user.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.cloud.data.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wsw
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String authName;

    @ApiModelProperty(value = "权限编码")
    private String authCode;

    @ApiModelProperty(value = "权限类型（SYSTEM，MENU，BUTTON）")
    private String authType;

    @ApiModelProperty(value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(value = "父级权限")
    private Long parentId;

    @ApiModelProperty(value = "是否有效（0否1是）")
    private Boolean isValid;


}
