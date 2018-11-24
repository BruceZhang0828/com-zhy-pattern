package com.zhy.pattern.templateMethod.dao;

import com.zhy.pattern.templateMethod.JdbcTemplate;
import com.zhy.pattern.templateMethod.RowMapper;
import com.zhy.pattern.templateMethod.entity.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(null);



    public List<Object> query(){
        String sql = "select * from `t_member`";
        //进行查询操作
        List<Object> members = jdbcTemplate.executeQuery(sql, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setAddr(rs.getString("addr"));
                member.setAge(rs.getInt("age"));
                return member;
            }
        }, null);
        return members;
    }

   /* @Override
    public Object processResult(ResultSet resultSet, int rownum) throws SQLException {
        Member member = new Member();
        member.setUsername(resultSet.getString("username"));
        member.setAddr(resultSet.getString("addr"));
        member.setAge(resultSet.getInt("age"));
        return member;
    }*/
}
