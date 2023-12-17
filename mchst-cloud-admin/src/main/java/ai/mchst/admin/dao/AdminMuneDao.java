package ai.mchst.admin.dao;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.entity.AdminMenuEntity;
import ai.mchst.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface AdminMuneDao extends BaseDao<AdminMenuEntity> {
    Set<String> getUserAuthority(@Param("id")Long id);
}
