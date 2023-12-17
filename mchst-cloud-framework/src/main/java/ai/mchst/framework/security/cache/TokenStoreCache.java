package ai.mchst.framework.security.cache;

import ai.mchst.framework.common.cache.RedisCache;
import ai.mchst.framework.common.cache.RedisKeys;
import ai.mchst.framework.security.properties.SecurityProperties;
import ai.mchst.framework.security.user.UserDetail;
import cn.hutool.core.collection.ListUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 认证 Cache
 */
@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final RedisCache redisCache;
    private final SecurityProperties securityProperties;

    public void saveUser(String accessToken, UserDetail user) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.set(key, user, securityProperties.getAccessTokenExpire());
    }

    public void saveUser(String accessToken, UserDetail user, long expire) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.set(key, user, expire);
    }

    public Long getExpire(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);

        return redisCache.getExpire(key);
    }

    public UserDetail getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return (UserDetail) redisCache.get(key);
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }

    public List<String> getUserKeyList() {
        String pattern = RedisKeys.getAccessTokenKey("*");
        Set<String> sets = redisCache.keys(pattern);

        return ListUtil.toList(sets);
    }

    public UserDetail getUserAdmin(String accessToken) {
        return (UserDetail)redisCache.get(accessToken);
    }

    public void ref(String accessToken) {
        redisCache.expire(accessToken,RedisCache.HOUR_ONE_EXPIRE);
    }
}
