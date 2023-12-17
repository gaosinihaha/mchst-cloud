package ai.mchst.admin.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
/**
 * @title: ToEmail
 */
@Data
@Component
@ConfigurationProperties(prefix = "constant.mail")
public class ToEmail implements Serializable {
    /**
     * 邮件接收方
     */
    private String to;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
