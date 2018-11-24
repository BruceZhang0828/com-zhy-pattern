package com.zhy.pattern.templateMethod;

import sun.plugin.navig.motif.OJIPlugin;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection creatConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    private PreparedStatement createPreparedStatement(Connection conn,String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private ResultSet execulteQuery(PreparedStatement pstmt,Object[] values) throws SQLException {

        for (int i = 0; i <values.length ; i++) {
            pstmt.setObject(i,values[i]);
        }

        return pstmt.executeQuery();
    }


    private void closeRs(ResultSet rs) throws SQLException {
        rs.close();
    }

    private void closePstmt(PreparedStatement pstmt) throws SQLException {
        pstmt.close();
    }


    private List<Object> parseResultSet(ResultSet rs,RowMapper rowMapper) throws SQLException{

        ArrayList<Object> results = new ArrayList<>();
            int rownum = 1;
            if(rs.next()){
                results.add(rowMapper.mapRow(rs,rownum));
            }

            return results;

    };
    public List<Object> executeQuery(String sql, RowMapper<?> rowMapper,Object[] values){
        try {
            //1.获取链接
            Connection conn = this.creatConnection();
            //2.创建语句集
            PreparedStatement pstmt = this.createPreparedStatement(conn, sql);
            //3.执行语句集 并且获取结果集
            ResultSet resultSet = this.execulteQuery(pstmt, values);
            //4.解析语句集
//            ArrayList<Object> results = new ArrayList<>();
//            int rownum = 1;
//            if(resultSet.next()){
//                results.add(processResult(resultSet,rownum));
//            }
            List<Object> results = this.parseResultSet(resultSet, rowMapper);
            //5.关闭结果集
            this.closeRs(resultSet);
            //6.关闭语句集
            this.closePstmt(pstmt);
            //7.关闭链接
            //conn.close();

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

   // public abstract Object processResult(ResultSet resultSet, int rownum) throws SQLException;
}
