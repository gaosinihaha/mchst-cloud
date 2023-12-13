package ai.mchst.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户Token
 */

@Data
@TableName("sys_user_token")
public class SysUserTokenEntity {

    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * accessToken 过期时间
     */
    private Date accessTokenExpire;

    /**
     * refreshToken
     */
    private String refreshToken;

    /**
     * refreshToken 过期时间
     */
    private Date refreshTokenExpire;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}