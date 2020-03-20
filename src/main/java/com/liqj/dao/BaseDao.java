package com.liqj.dao;

import com.liqj.utils.DButil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Liqj
 * @date 2020/3/17 18:02
 */
public class BaseDao {
    public JdbcTemplate template=new JdbcTemplate(DButil.getDataSource());
}
