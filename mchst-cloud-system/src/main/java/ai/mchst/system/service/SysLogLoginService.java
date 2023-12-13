package ai.mchst.system.service;

import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysLogLoginEntity;
import ai.mchst.system.query.SysLogLoginQuery;
import ai.mchst.system.vo.SysLogLoginVO;
import ai.mchst.framework.common.utils.PageResult;

/**
 * 登录日志
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    /**
     * Page result.
     *
     * @param query the query
     * @return the page result
     */
    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);

    /**
     * 导出登录日志
     */
    void export();
}