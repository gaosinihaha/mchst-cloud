package ai.mchst.system.service;


import ai.mchst.framework.mybatis.service.BaseService;
import ai.mchst.system.entity.SysAttachmentEntity;
import ai.mchst.system.query.SysAttachmentQuery;
import ai.mchst.system.vo.SysAttachmentVO;
import ai.mchst.framework.common.utils.PageResult;

import java.util.List;

/**
 * 附件管理
 */
public interface SysAttachmentService extends BaseService<SysAttachmentEntity> {

    PageResult<SysAttachmentVO> page(SysAttachmentQuery query);

    void save(SysAttachmentVO vo);

    void update(SysAttachmentVO vo);

    void delete(List<Long> idList);
}