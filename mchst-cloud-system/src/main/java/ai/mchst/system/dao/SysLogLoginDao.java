package ai.mchst.system.dao;

import ai.mchst.framework.mybatis.dao.BaseDao;
import ai.mchst.system.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}