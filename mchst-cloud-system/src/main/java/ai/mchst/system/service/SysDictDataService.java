package ai.mchst.system.service;


import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysDictDataEntity;
import ai.mchst.system.query.SysDictDataQuery;
import ai.mchst.system.vo.SysDictDataVO;
import ai.mchst.framework.common.utils.PageResult;

import java.util.List;

/**
 * 数据字典
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageResult<SysDictDataVO> page(SysDictDataQuery query);

    void save(SysDictDataVO vo);

    void update(SysDictDataVO vo);

    void delete(List<Long> idList);

}