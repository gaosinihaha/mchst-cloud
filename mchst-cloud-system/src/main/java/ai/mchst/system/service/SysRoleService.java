package ai.mchst.system.service;


import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysRoleEntity;
import ai.mchst.system.query.SysRoleQuery;
import ai.mchst.system.vo.SysRoleDataScopeVO;
import ai.mchst.system.vo.SysRoleVO;
import ai.mchst.framework.common.utils.PageResult;

import java.util.List;

/**
 * 角色
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRoleVO vo);

	void update(SysRoleVO vo);

	void dataScope(SysRoleDataScopeVO vo);

	void delete(List<Long> idList);
}
