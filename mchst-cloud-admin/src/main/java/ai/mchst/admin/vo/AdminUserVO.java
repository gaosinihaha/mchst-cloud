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
public class AdminUserVO extends BaseEntity {

    private String verifyCode;

    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别   0：男   1：女   2：未知
     */
    private Integer gender;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 超级管理员   0：否   1：是
     */
    private Integer superAdmin;
    /**
     * 状态  {@link }
     */
    private Integer status;
}
