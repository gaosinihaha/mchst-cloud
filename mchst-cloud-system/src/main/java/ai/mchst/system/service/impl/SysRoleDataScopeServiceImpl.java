package ai.mchst.system.service.impl;

import ai.mchst.framework.mybatis.service.impl.BaseServiceImpl;
import ai.mchst.system.dao.SysRoleDataScopeDao;
import ai.mchst.system.entity.SysRoleDataScopeEntity;
import ai.mchst.system.service.SysRoleDataScopeService;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色数据权限
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeDao, SysRoleDataScopeEntity>
        implements SysRoleDataScopeService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> orgIdList) {
        // 数据库机构ID列表
        List<Long> dbOrgIdList = getOrgIdList(roleId);

        // 需要新增的机构ID
        Collection<Long> insertOrgIdList = CollUtil.subtract(orgIdList, dbOrgIdList);
        if (CollUtil.isNotEmpty(insertOrgIdList)){
            List<SysRoleDataScopeEntity> orgList = insertOrgIdList.stream().map(orgId -> {
                SysRoleDataScopeEntity entity = new SysRoleDataScopeEntity();
                entity.setOrgId(orgId);
                entity.setRoleId(roleId);
                return entity;
            }).collect(Collectors.toList());

            // 批量新增
            saveBatch(orgList);
        }

        // 需要删除的机构ID
        Collection<Long> deleteOrgIdList = CollUtil.subtract(dbOrgIdList, orgIdList);
        if (CollUtil.isNotEmpty(deleteOrgIdList)){
            LambdaQueryWrapper<SysRoleDataScopeEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRoleDataScopeEntity::getRoleId, roleId);
            queryWrapper.in(SysRoleDataScopeEntity::getOrgId, deleteOrgIdList);

            remove(queryWrapper);
        }
    }

    @Override
    public List<Long> getOrgIdList(Long roleId) {
        return baseMapper.getOrgIdList(roleId);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        remove(new LambdaQueryWrapper<SysRoleDataScopeEntity>().in(SysRoleDataScopeEntity::getRoleId, roleIdList));
    }
}