package ai.mchst.system.entity;

import ai.mchst.framework.mybatis.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 机构管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_org")
public class SysOrgEntity extends BaseEntity {
    /**
     * 上级ID
     */
    private Long pid;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 上级名称
     */
    @TableField(exist = false)
    private String parentName;
    /**
     * 租户ID
     */
    private Long tenantId;
}