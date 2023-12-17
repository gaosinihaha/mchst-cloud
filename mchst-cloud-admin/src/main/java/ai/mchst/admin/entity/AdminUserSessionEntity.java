package ai.mchst.admin.entity;

import ai.mchst.framework.mybatis.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admin_user_session")
public class AdminUserSessionEntity extends BaseEntity {
    private Long userId;

    private Long sessionId;

    private String  sessionCode;

}