package ai.mchst.system.query;

import ai.mchst.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "岗位管理查询")
public class SysPostQuery extends Query {
    @Schema(description = "岗位编码")
    private String postCode;

    @Schema(description = "岗位名称")
    private String postName;

    @Schema(description = "状态  0：停用   1：正常")
    private Integer status;

}
