package ai.mchst.system.service;

import ai.mchst.framework.common.utils.PageResult;
import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysPostEntity;
import ai.mchst.system.query.SysPostQuery;
import ai.mchst.system.vo.SysPostVO;

import java.util.List;

/**
 * 岗位管理
 */
public interface SysPostService extends BaseService<SysPostEntity> {

    PageResult<SysPostVO> page(SysPostQuery query);

    List<SysPostVO> getList();

    void save(SysPostVO vo);

    void update(SysPostVO vo);

    void delete(List<Long> idList);
}