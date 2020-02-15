package com.harvey.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisConfig {
	@Value("${spring.redis.host}")
	public String redisHost;
	@Value("${spring.redis.port}")
	public int redisPort;
	@Value("${spring.redis.password}")
	public String redisPasswd;
	@Value("${spring.redis.database}")
	public int redisDatabase;

	@Bean // redis配置
	public RedisClusterConfiguration getClusterConfiguration() {
		Map<String, Object> source = new HashMap<String, Object>(16);
		return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
	}

	@Bean // redis连接
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory cf = null;
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHost);
		redisStandaloneConfiguration.setPort(redisPort);
		redisStandaloneConfiguration.setDatabase(redisDatabase);
		redisStandaloneConfiguration.setPassword(redisPasswd);
		cf = new JedisConnectionFactory(redisStandaloneConfiguration);
		cf.afterPropertiesSet();
		return cf;
	}

	@Bean // 实际使用的redisTemplate，可以直接注入到代码中，直接操作redis
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		RedisSerializer<?> stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		redisTemplate.setDefaultSerializer(stringSerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean // 关联redis到注解
	CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

		// 默认配置，过期时间指定是30分钟
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		defaultCacheConfig.entryTtl(Duration.ofMinutes(30));

		// redisExpire1h cache配置，过期时间指定是1小时，缓存key的前缀指定成prefixaaa_（存到redis的key会自动添加这个前缀）
		RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
		        .entryTtl(Duration.ofHours(1)).prefixKeysWith("yhw_");
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		redisCacheConfigurationMap.put("redisExpire1h", userCacheConfiguration);
		RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig,
		        redisCacheConfigurationMap);
		return cacheManager;
	}
}