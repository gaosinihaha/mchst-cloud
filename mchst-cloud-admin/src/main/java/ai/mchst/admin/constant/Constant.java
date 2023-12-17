package ai.mchst.admin.constant;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

public  interface Constant {

    interface MailConstant{
       String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

       String MAIL_EXPIRE_MESSAGE="验证码有效时间为120秒，请勿频繁发送！！！";

       String SUCCESS = "发送成功";
       String ERROR = "发送失败";

       String VERIFY_ERROR = "验证码不正确或过期，请重新发送";
       String EMAIL_EORROR = "当前输入邮件格式不正确！！";

       String EMAIL_NAME ="mchst_ai@163.com";
    }
}
