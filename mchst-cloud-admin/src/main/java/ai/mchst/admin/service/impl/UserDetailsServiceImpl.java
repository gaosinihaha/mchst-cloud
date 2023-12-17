package ai.mchst.admin.service.impl;

import ai.mchst.admin.service.AdminUserService;
import ai.mchst.framework.security.user.UserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 账号登录 UserDetailsService
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminUserService adminUserService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetail userEntity = adminUserService.getByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return adminUserService.getUserDetails(userEntity);
    }

}
