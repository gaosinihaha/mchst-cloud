package ai.mchst.system.service;

import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysDictTypeEntity;
import ai.mchst.system.query.SysDictTypeQuery;
import ai.mchst.system.vo.SysDictTypeVO;
import ai.mchst.system.vo.SysDictVO;
import ai.mchst.framework.common.utils.PageResult;

import java.util.List;

/**
 * 数据字典
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageResult<SysDictTypeVO> page(SysDictTypeQuery query);

    void save(SysDictTypeVO vo);

    void update(SysDictTypeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取动态SQL数据
     */
    List<SysDictVO.DictData> getDictSql(Long id);

    /**
     * 获取全部字典列表
     */
    List<SysDictVO> getDictList();

    /**
     * 刷新字典缓存
     */
    void refreshTransCache();

}