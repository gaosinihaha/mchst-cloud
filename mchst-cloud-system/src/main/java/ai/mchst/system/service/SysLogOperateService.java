package ai.mchst.system.service;


import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysLogOperateEntity;
import ai.mchst.system.query.SysLogOperateQuery;
import ai.mchst.system.vo.SysLogOperateVO;
import ai.mchst.framework.common.utils.PageResult;

/**
 * 操作日志
 */
public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    PageResult<SysLogOperateVO> page(SysLogOperateQuery query);
}