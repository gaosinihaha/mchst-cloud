package ai.mchst.admin.controller;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.convert.AdminUserConvert;
import ai.mchst.admin.service.AdminUserService;
import ai.mchst.admin.vo.AdminUserVO;
import ai.mchst.framework.common.utils.Result;
import ai.mchst.framework.security.user.SecurityUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@Tag(name = "前台系统")
public class AdminUserController {
    private final AdminUserService adminUserService;

    @Operation(summary = "发送注册邮件")
    @GetMapping("send/{email}")
    public Result<String> register(@PathVariable("email") String email){
         return adminUserService.sendEmail(email);
    }

    @Operation(summary = "注册")
    @PostMapping("register")
    public Result<String> register(@RequestBody AdminUserVO adminUserVO){
        return adminUserService.register(adminUserVO);
    }

    @PostMapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody AdminUserVO adminUserVO){
        return adminUserService.login(AdminUserConvert.INSTANCE.convert(adminUserVO));
    }
    @GetMapping("info")
    @Operation(summary = "用户详情")
    public Result<AdminUserVO> info() {
        AdminUserVO user = AdminUserConvert.INSTANCE.convert(SecurityUser.getUser());
        return Result.ok(user);
    }
}
