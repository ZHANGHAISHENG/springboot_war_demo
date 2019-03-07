package com.hamlt.springboot.service;

import com.hamlt.springboot.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    //默认配置可以无需声明@Qualifier
    /*@Autowired
    private JdbcTemplate jdbcTemplate;*/

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate  jdbcTemplate2;


    // 查询所有用户
    public List<User> getUsers() {
        String sql = "select * from t_user";
        return jdbcTemplate1.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    @Transactional//(value="txManager1")
    public boolean deleteUser() {
        String sql = "delete from t_user where username='zhs'";
        int i = jdbcTemplate1.update(sql);
        if (true) {
            throw new RuntimeException("save 抛异常了");
        }
        return i > 0;
    }
}
