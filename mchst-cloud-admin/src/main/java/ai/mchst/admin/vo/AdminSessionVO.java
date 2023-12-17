package ai.mchst.admin.vo;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.framework.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSessionVO extends BaseEntity {

    private String question;

    private String answer;
}
