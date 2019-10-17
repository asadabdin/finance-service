package org.asad.finance.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.config.CacheConfiguration;
import org.asad.finance.config.properties.EhCacheProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class EhCacheConfiguration extends CachingConfigurerSupport {

    private final EhCacheProperties ehCacheProperties;

    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        log.info("creating cache for [{}]", ehCacheProperties.getCacheName());
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName(ehCacheProperties.getCacheName());
        cacheConfiguration.setMaxEntriesLocalHeap(ehCacheProperties.getMaxEntriesLocalHeap());
        cacheConfiguration.setTimeToLiveSeconds(ehCacheProperties.getExpiryIntervalInSeconds());

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);

        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
}
