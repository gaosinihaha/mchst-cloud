package ai.mchst.system.dao;

import ai.mchst.framework.mybatis.dao.BaseDao;
import ai.mchst.system.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 岗位管理
*/
@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {
	
}