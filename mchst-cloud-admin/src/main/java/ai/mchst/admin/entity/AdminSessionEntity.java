package ai.mchst.admin.entity;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.framework.mybatis.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_session")
public class AdminSessionEntity extends BaseEntity {

    private String question;

    private String answer;
}
