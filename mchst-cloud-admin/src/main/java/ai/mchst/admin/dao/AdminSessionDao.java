package ai.mchst.admin.dao;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.entity.AdminSessionEntity;
import ai.mchst.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminSessionDao extends BaseDao<AdminSessionEntity> {
}
