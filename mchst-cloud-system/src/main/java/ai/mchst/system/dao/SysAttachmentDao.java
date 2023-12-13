package ai.mchst.system.dao;


import ai.mchst.framework.mybatis.dao.BaseDao;
import ai.mchst.system.entity.SysAttachmentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件管理
 */
@Mapper
public interface SysAttachmentDao extends BaseDao<SysAttachmentEntity> {

}