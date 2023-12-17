package ai.mchst.admin.dao;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.entity.AdminUserEntity;
import ai.mchst.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserDao extends BaseDao<AdminUserEntity> {
}
