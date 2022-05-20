package com.consumer.infrastructure;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.consumer.infrastructure.DataSourceConfiguration.DataSourcePoolProperties;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourcePoolProperties.class)
class DataSourceConfiguration {

    @Getter
    @Setter
    @ConfigurationProperties("app.sqlserver.datasource")
    public static class DataSourcePoolProperties extends PoolProperties {

        @NotNull
        private Integer port;

        @NotBlank
        private String hostname;
    }

    @Bean
    public DataSource dataSource(final DataSourcePoolProperties dataSourcePoolProperties) {
        final DataSource dataSource = new DataSource();
        dataSource.setUrl(dataSourcePoolProperties.getUrl());
        dataSource.setDriverClassName(dataSourcePoolProperties.getDriverClassName());
        dataSource.setUsername(dataSourcePoolProperties.getUsername());
        dataSource.setPassword(dataSourcePoolProperties.getPassword());
        dataSource.setValidationQuery(dataSourcePoolProperties.getValidationQuery());
        dataSource.setTimeBetweenEvictionRunsMillis(dataSourcePoolProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMaxActive(dataSourcePoolProperties.getMaxActive());
        dataSource.setMinIdle(dataSourcePoolProperties.getMinIdle());
        dataSource.setMaxIdle(dataSourcePoolProperties.getMaxIdle());
        dataSource.setInitialSize(dataSourcePoolProperties.getInitialSize());
        dataSource.setRemoveAbandonedTimeout(dataSourcePoolProperties.getRemoveAbandonedTimeout());
        dataSource.setMinEvictableIdleTimeMillis(dataSourcePoolProperties.getMinEvictableIdleTimeMillis());
        dataSource.setSuspectTimeout(dataSourcePoolProperties.getSuspectTimeout());
        dataSource.setTestOnBorrow(dataSourcePoolProperties.isTestOnBorrow());
        dataSource.setRemoveAbandoned(dataSourcePoolProperties.isRemoveAbandoned());
        return dataSource;
    }
}
