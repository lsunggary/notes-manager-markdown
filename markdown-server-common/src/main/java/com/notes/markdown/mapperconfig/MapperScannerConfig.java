package com.notes.markdown.mapperconfig;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // mapper 类的包名 --即炒作数据库库的对应到数据表的Mapper.java类的包名。
        mapperScannerConfigurer.setBasePackage("com.notes.markdown.mapper");

        Properties properties = new Properties();
        //properties.setProperty();

        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("ORDER", "BEFORE");

        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
