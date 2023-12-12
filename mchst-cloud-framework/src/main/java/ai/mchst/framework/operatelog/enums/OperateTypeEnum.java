package ai.mchst.framework.operatelog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {
    /**
     * 查询
     */
    GET(1),
    /**
     * 新增
     */
    INSERT(2),
    /**
     * 修改
     */
    UPDATE(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 导出
     */
    EXPORT(5),
    /**
     * 导入
     */
    IMPORT(6),
    /**
     * 其它
     */
    OTHER(0);

    private final int value;
}
