package com.dos.protection.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * All configuration from application.properties
 *
 * @auther Karine Camhy
 * @since 8/1/2020
 */

@ConfigurationProperties("client.request")
public class ClientProperties {

    private static final int DEFAULT_VAL = 5;
    private int maxCount;
    private int maxTime;
    private String unit;

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public TimeUnit getUnitTime() {
        if(unit == null)
            return TimeUnit.SECONDS;
        switch (unit){
            case "MICROSECONDS": return TimeUnit.MICROSECONDS;
            case "MINUTES": return TimeUnit.MINUTES;
            default:
               return TimeUnit.SECONDS;
        }
    }
}
