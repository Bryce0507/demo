package com.dingding.config;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

/**
 * @author zxb
 * @date 2020/9/1 10:54
 */
public class SeqShardingKeyGenerator implements ShardingKeyGenerator {


    private Properties properties = new Properties();

    @Override
    public String getType() {
        return "SEQ";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Comparable<?> generateKey() {
        return null;
    }
}
