package ai.mchst.system.controller;

import ai.mchst.framework.common.utils.Result;
import ai.mchst.framework.operatelog.annotations.OperateLog;
import ai.mchst.framework.operatelog.enums.OperateTypeEnum;
import ai.mchst.framework.security.user.SecurityUser;
import ai.mchst.framework.security.user.UserDetail;
import ai.mchst.system.convert.SysRoleConvert;
import ai.mchst.system.entity.SysRoleEntity;
import ai.mchst.system.query.SysRoleQuery;
import ai.mchst.system.query.SysRoleUserQuery;
import ai.mchst.system.service.*;
import ai.mchst.system.vo.SysMenuVO;
import ai.mchst.system.vo.SysRoleDataScopeVO;
import ai.mchst.system.vo.SysRoleVO;
import ai.mchst.system.vo.SysUserVO;
import ai.mchst.framework.common.utils.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 */
@RestController
@RequestMapping("sys/role")
@Tag(name = "角色管理")
@AllArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysUserService sysUserService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;
    private final SysMenuService sysMenuService;
    private final SysUserRoleService sysUserRoleService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:role:page')")
    public Result<PageResult<SysRoleVO>> page(@ParameterObject @Valid SysRoleQuery query) {
        PageResult<SysRoleVO> page = sysRoleService.page(query);

        return Result.ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result<List<SysRoleVO>> list() {
        List<SysRoleVO> list = sysRoleService.getList(new SysRoleQuery());

        return Result.ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:role:info')")
    public Result<SysRoleVO> get(@PathVariable("id") Long id) {
        SysRoleEntity entity = sysRoleService.getById(id);

        // 转换对象
        SysRoleVO role = SysRoleConvert.INSTANCE.convert(entity);

        // 查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
        role.setMenuIdList(menuIdList);

        // 查询角色对应的数据权限
        List<Long> orgIdList = sysRoleDataScopeService.getOrgIdList(id);
        role.setOrgIdList(orgIdList);

        return Result.ok(role);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:role:save')")
    public Result<String> save(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<String> update(@RequestBody @Valid SysRoleVO vo) {
        sysRoleService.update(vo);

        return Result.ok();
    }

    @PutMapping("data-scope")
    @Operation(summary = "数据权限")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<String> dataScope(@RequestBody @Valid SysRoleDataScopeVO vo) {
        sysRoleService.dataScope(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysRoleService.delete(idList);

        return Result.ok();
    }

    @GetMapping("menu")
    @Operation(summary = "角色菜单")
    @PreAuthorize("hasAuthority('sys:role:menu')")
    public Result<List<SysMenuVO>> menu() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuVO> list = sysMenuService.getUserMenuList(user, null);

        return Result.ok(list);
    }

    @GetMapping("user/page")
    @Operation(summary = "角色用户-分页")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<PageResult<SysUserVO>> userPage(@Valid SysRoleUserQuery query) {
        PageResult<SysUserVO> page = sysUserService.roleUserPage(query);

        return Result.ok(page);
    }

    @DeleteMapping("user/{roleId}")
    @Operation(summary = "删除角色用户")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<String> userDelete(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
        sysUserRoleService.deleteByUserIdList(roleId, userIdList);

        return Result.ok();
    }

    @PostMapping("user/{roleId}")
    @Operation(summary = "分配角色给用户列表")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result<String> userSave(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIdList) {
        sysUserRoleService.saveUserList(roleId, userIdList);

        return Result.ok();
    }
}