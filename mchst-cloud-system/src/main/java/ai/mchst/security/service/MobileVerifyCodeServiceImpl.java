package ai.mchst.security.service;

import ai.mchst.api.module.message.SmsApi;
import ai.mchst.framework.security.mobile.MobileVerifyCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 短信验证码效验
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    private final SmsApi smsApi;

    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsApi.verifyCode(mobile, code);
    }
}
