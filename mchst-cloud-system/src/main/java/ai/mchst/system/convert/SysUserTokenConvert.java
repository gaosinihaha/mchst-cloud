package ai.mchst.system.convert;

import ai.mchst.system.entity.SysUserTokenEntity;
import ai.mchst.system.vo.SysUserTokenVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户Token
 */
@Mapper
public interface SysUserTokenConvert {
    SysUserTokenConvert INSTANCE = Mappers.getMapper(SysUserTokenConvert.class);

    SysUserTokenEntity convert(SysUserTokenVO vo);

    SysUserTokenVO convert(SysUserTokenEntity entity);

}