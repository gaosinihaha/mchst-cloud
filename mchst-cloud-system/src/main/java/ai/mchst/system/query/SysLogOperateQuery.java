package ai.mchst.system.query;

import ai.mchst.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志查询
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "操作日志查询")
public class SysLogOperateQuery extends Query {
    @Schema(description = "用户")
    private String realName;

    @Schema(description = "模块名")
    private String module;

    @Schema(description = "请求URI")
    private String reqUri;

    @Schema(description = "操作状态")
    private Integer status;

}