package ai.mchst.system.service.impl;

import ai.mchst.framework.common.cache.RedisCache;
import ai.mchst.framework.common.cache.RedisKeys;
import ai.mchst.system.enums.SysParamsEnum;
import ai.mchst.system.service.SysCaptchaService;
import ai.mchst.system.service.SysParamsService;
import ai.mchst.system.vo.SysCaptchaVO;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 验证码
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {
    private final RedisCache redisCache;
    private final SysParamsService sysParamsService;

    @Override
    public SysCaptchaVO generate() {
        // 生成验证码key
        String key = UUID.randomUUID().toString();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = RedisKeys.getCaptchaKey(key);
        redisCache.set(redisKey, captcha.text(), 300);

        // 封装返回数据
        SysCaptchaVO captchaVO = new SysCaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImage(image);

        return captchaVO;
    }

    @Override
    public boolean validate(String key, String code) {
        // 如果关闭了验证码，则直接效验通过
        if (!isCaptchaEnabled()) {
            return true;
        }

        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    @Override
    public boolean isCaptchaEnabled() {
        return sysParamsService.getBoolean(SysParamsEnum.LOGIN_CAPTCHA.name());
    }

    private String getCache(String key) {
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisCache.get(key);
        // 删除验证码
        if (captcha != null) {
            redisCache.delete(key);
        }

        return captcha;
    }

}
