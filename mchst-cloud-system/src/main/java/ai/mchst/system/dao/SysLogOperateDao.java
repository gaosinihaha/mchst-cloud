package ai.mchst.system.dao;

import ai.mchst.framework.mybatis.dao.BaseDao;
import ai.mchst.system.entity.SysLogOperateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 */
@Mapper
public interface SysLogOperateDao extends BaseDao<SysLogOperateEntity> {

}