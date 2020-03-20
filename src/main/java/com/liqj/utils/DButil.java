package com.liqj.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Liqj
 * @date 2020/3/17 17:51
 */
public class DButil {
    private static DruidDataSource druidDataSource;

    public static void main(String[] args) {
        JdbcTemplate template=new JdbcTemplate(getDataSource());
        System.out.println("测试JdbcTemplate连接:"+template);
    }

    static {
        Properties pro=new Properties();
        try {
            pro.load(DButil.class.getClassLoader().getResourceAsStream("db.properties"));
            druidDataSource=new DruidDataSource();
            druidDataSource.setUsername(pro.getProperty("jdbc.username"));
            druidDataSource.setPassword(pro.getProperty("jdbc.password"));
            druidDataSource.setUrl(pro.getProperty("jdbc.url"));
            druidDataSource.setDriverClassName(pro.getProperty("jdbc.driverClassName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Liqj
     * @date 2020/3/17
     * @params []
     * @return com.alibaba.druid.pool.DruidDataSource
     * @description 获取数据源
     */
    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }
}
