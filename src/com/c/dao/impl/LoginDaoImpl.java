package com.c.dao.impl;

import com.c.dao.DruidUtils;
import com.c.dao.LogInDao;
import com.c.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginDaoImpl implements LogInDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    /**
     *
     * @param user  只有用户名和面
     * @return  返回用户所有数据
     */
    @Override
    public User queryxThereUser(User user) {
        User loginData = null;
        try {
            loginData= jdbcTemplate.queryForObject("select * from login where userName=? and password=?", new BeanPropertyRowMapper<User>(User.class), user.getUserName(), user.getPassword());
            return loginData;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
