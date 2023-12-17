package ai.mchst.admin.service;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.entity.AdminUserEntity;
import ai.mchst.admin.vo.AdminUserVO;
import ai.mchst.framework.common.utils.Result;
import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.framework.security.user.UserDetail;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminUserService extends BaseService<AdminUserEntity> {
    /**
     * 根据email获取用户详细信息
     * @param username
     * @return
     */
    UserDetail getByEmail(String username);
    /**
     * 获取用户权限-
     * @param userEntity
     * @return
     */
    UserDetails getUserDetails(UserDetail userEntity);
    /**
     * 注册获取邮件
     * @param email
     */
    Result sendEmail(String email);
    /**
     * 注册
     * @param adminUserVO
     * @return
     */
    Result<String> register(AdminUserVO adminUserVO);

    /**
     * 登录
     * @param convert
     * @return
     */
    Result<String> login(AdminUserEntity convert);
}
