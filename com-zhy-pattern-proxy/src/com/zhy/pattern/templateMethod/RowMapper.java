package com.zhy.pattern.templateMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    //正在结果集处理
    public T mapRow(ResultSet rs,int rowNum) throws SQLException;
}
