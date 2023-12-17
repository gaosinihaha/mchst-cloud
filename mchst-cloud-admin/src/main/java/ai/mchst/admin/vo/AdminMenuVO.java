package ai.mchst.admin.vo;

import ai.mchst.framework.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMenuVO extends BaseEntity {
    /**
     * 上级ID
     */
    private Long pid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权标识(多个用逗号分隔，如：sys:menu:list,sys:menu:save)
     */
    private String authority;
    /**
     * 类型   0：菜单   1：按钮   2：接口
     */
    private Integer type;
    /**
     * 打开方式   0：内部   1：外部
     */
    private Integer openStyle;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;

}