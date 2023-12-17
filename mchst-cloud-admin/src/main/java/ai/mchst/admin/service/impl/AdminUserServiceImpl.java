package ai.mchst.admin.service.impl;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.constant.Constant;
import ai.mchst.admin.convert.AdminUserConvert;
import ai.mchst.admin.dao.AdminMuneDao;
import ai.mchst.admin.dao.AdminUserDao;
import ai.mchst.admin.entity.AdminUserEntity;
import ai.mchst.admin.enums.UserStatusEnum;
import ai.mchst.admin.service.AdminUserService;
import ai.mchst.admin.vo.AdminUserVO;
import ai.mchst.admin.vo.ToEmail;
import ai.mchst.framework.common.cache.RedisCache;
import ai.mchst.framework.common.exception.ServerException;
import ai.mchst.framework.common.utils.Result;
import ai.mchst.framework.mybatis.service.impl.BaseServiceImpl;
import ai.mchst.framework.security.user.UserDetail;
import ai.mchst.framework.security.utils.TokenUtils;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserDao, AdminUserEntity> implements AdminUserService {
    private final  AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RedisCache redisCache;
    private final AdminUserDao adminUserDao;
    private final AdminMuneDao adminMuneDao;
    private final JavaMailSender mailSender;
    private final ToEmail toEmail;

    @Override
    public UserDetail getByEmail(String email) {
        AdminUserEntity adminUserEntity = adminUserDao.selectOne(new LambdaQueryWrapper<AdminUserEntity>().eq(AdminUserEntity::getEmail, email));
        return AdminUserConvert.INSTANCE.convert(adminUserEntity);
    }

    @Override
    public UserDetails getUserDetails(UserDetail userEntity) {
        if (userEntity.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userEntity.setEnabled(false);
        }
        Set<String> authoritySet = adminMuneDao.getUserAuthority(userEntity.getId());
        userEntity.setAuthoritySet(authoritySet);

        return userEntity;
    }

    @Override
    public Result sendEmail(String email) {

        if (!this.isValidEmail(email)) {
            return Result.error(Constant.MailConstant.EMAIL_EORROR);
        }
        toEmail.setTo(email);
        if (redisCache.hasKey(email))
            return Result.error(Constant.MailConstant.MAIL_EXPIRE_MESSAGE);

        if (this.isEmailNull(toEmail)) {
            Random random = new Random();
            int i = random.nextInt(100000, 999999);

            redisCache.set(email, i, 120L);
            //创建简单邮件消息
            SimpleMailMessage message = new SimpleMailMessage();
            //谁发的
            message.setFrom(Constant.MailConstant.EMAIL_NAME);
            //谁要接收
            message.setTo(toEmail.getTo());
            //邮件标题
            message.setSubject(toEmail.getSubject());
            //邮件内容
            message.setText(toEmail.getContent() + i);
            try {
                mailSender.send(message);
                return Result.ok(toEmail.getTo());
            } catch (MailException e) {
                e.printStackTrace();
                return Result.error(Constant.MailConstant.SUCCESS);
            }
        }
        return Result.error("当前用户已注册！！");
    }

    @Override
    public Result<String> register(AdminUserVO adminUserVO) {
        try {
            if (Objects.isNull(redisCache.get(adminUserVO.getEmail())) || !adminUserVO.getVerifyCode().equals(redisCache.get(adminUserVO.getEmail()).toString())) {
                return Result.error(Constant.MailConstant.VERIFY_ERROR);
            }
            //验证是否注册过
            boolean empty = baseMapper.selectList(new LambdaQueryWrapper<AdminUserEntity>()
                    .eq(AdminUserEntity::getEmail, adminUserVO.getEmail())).isEmpty();
            if (!empty){
                return Result.error("当前用户已注册！！");
            }
            //验证通过
            AdminUserEntity adminUserEntity = AdminUserConvert.INSTANCE.convert(adminUserVO);
            adminUserEntity.setUsername(adminUserEntity.getEmail());

            if (StrUtil.isBlank(adminUserEntity.getPassword())) {
                return Result.error("密码不能为空");
            }

            adminUserEntity.setPassword(passwordEncoder.encode(adminUserVO.getPassword()));
            adminUserEntity.setStatus(1);
            adminUserEntity.setSuperAdmin(0);

            baseMapper.insert(adminUserEntity);

            return Result.ok();
        } catch (IllegalArgumentException e) {
            return Result.error(Constant.MailConstant.VERIFY_ERROR);
        }

    }

    @Override
    public Result<String> login(AdminUserEntity entity) {

        Authentication authentication;

        AdminUserEntity adminUserEntity = baseMapper.selectOne(new LambdaQueryWrapper<AdminUserEntity>().eq(AdminUserEntity::getEmail, entity.getEmail()));
        if (Objects.isNull(adminUserEntity)) return Result.error("当前用户未注册！请先完成注册");

        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();
        user.setPassword(null);
        // 生成 accessToken
        String accessToken = TokenUtils.generator();
        redisCache.set(accessToken,user,RedisCache.HOUR_ONE_EXPIRE);
        return Result.ok(accessToken);
    }

    public boolean isEmailNull(ToEmail toEmail) {
        List<AdminUserEntity> adminUserEntities = baseMapper.selectList(new LambdaQueryWrapper<AdminUserEntity>().eq(AdminUserEntity::getEmail, toEmail.getTo()));
        if (adminUserEntities.isEmpty()) return true;
        else return false;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(Constant.MailConstant.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
