package com.dingding.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.core.config.DataSourceConfiguration;
import org.apache.shardingsphere.core.yaml.swapper.impl.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.spring.boot.common.SpringBootPropertiesConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.encrypt.SpringBootEncryptRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.masterslave.SpringBootMasterSlaveRuleConfigurationProperties;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.ShardingRuleCondition;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxb
 * @date 2020/9/1 10:08
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootShardingRuleConfigurationProperties.class,
        SpringBootMasterSlaveRuleConfigurationProperties.class,
        SpringBootEncryptRuleConfigurationProperties.class,
        SpringBootPropertiesConfigurationProperties.class
})
@AutoConfigureBefore(DataSourceConfiguration.class)
public class DataSourceConfig implements ApplicationContextAware {


    @Autowired
    private SpringBootShardingRuleConfigurationProperties shardingRule;

    @Autowired
    private SpringBootPropertiesConfigurationProperties props;

    private ApplicationContext applicationContext;



    @Bean("shardingDataSource")
    @Conditional(ShardingRuleCondition.class)
    public DataSource shardingDataSource() throws SQLException {

        Map<String, DruidDataSource> beans = applicationContext.getBeansOfType(DruidDataSource.class);

        Map<String, DataSource> dataSourceMap = new HashMap<>(4);

        beans.forEach(dataSourceMap::put);

        //创建shardingDataSource
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, new ShardingRuleConfigurationYamlSwapper().swap(shardingRule), props.getProps());

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //将shardingDataSource设置到sqlSessionFactory中
        sqlSessionFactoryBean.setDataSource(shardingDataSource());
        //其他设置
        return sqlSessionFactoryBean.getObject();
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
