package ai.mchst.system.query;

import ai.mchst.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "字典数据查询")
public class SysDictDataQuery extends Query {
    @Schema(description = "字典类型ID", required = true)
    @NotNull(message = "字典类型ID不能为空")
    private Long dictTypeId;

}
