package com.dos.protection.service;

import com.dos.protection.configuration.ClientProperties;
import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.cache2k.integration.CacheLoader;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.LoggerFactory;

/**
 * This class handles request cache with counter and time frame threshold
 * On loading class it will load properties and
 * create cache using Cache2k with client id key and value is request count
 *
 *
 * @auther Karine Camhy
 * @since 8/1/2020
 */

@EnableConfigurationProperties(ClientProperties.class)
@Service
public class DosProtectionService {

    private static final Logger logger = LoggerFactory.getLogger(DosProtectionService.class);

    private final ClientProperties clientProperties;
    private Cache<Integer,AtomicInteger> cache;
    @Autowired
    public DosProtectionService(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
        cache = new Cache2kBuilder<Integer, AtomicInteger>() {}
                .expireAfterWrite(clientProperties.getMaxTime(), clientProperties.getUnitTime()).loader(new CacheLoader<Integer, AtomicInteger> (){
                    /**
                     * init cache with clientIds and 0
                     * @param integer
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public AtomicInteger load(Integer integer) throws Exception {
                        return new AtomicInteger(0);
                    }
                }).build();
    }

    /**
     * It will check the cache by clientId if counter reached to maximum client request
     *
     * @param id Recieves client id
     * @return if counter is more than max client request count return false else true
     */
    public boolean isMaxRequestPerTime(Integer id){
        if(cache.get(id).incrementAndGet() > clientProperties.getMaxCount()) {
            return false;
        }
        return true;
    }


}
