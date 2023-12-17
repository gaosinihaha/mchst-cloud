package ai.mchst.admin.convert;/*
 * @Auther:xueya
 * @Date:2023/12/16
 * @Description:
 */

import ai.mchst.admin.entity.AdminUserEntity;
import ai.mchst.admin.vo.AdminUserVO;
import ai.mchst.framework.security.user.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminUserConvert {

    AdminUserConvert INSTANCE = Mappers.getMapper(AdminUserConvert.class);

    UserDetail convert(AdminUserEntity adminUserEntity);

    AdminUserEntity convert(AdminUserVO adminUserVO);

    AdminUserVO convert(UserDetail user);
}
