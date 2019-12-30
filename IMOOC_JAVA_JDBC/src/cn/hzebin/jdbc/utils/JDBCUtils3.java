package cn.hzebin.jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
使用C3P0的配置文件创建连接池，jdbc的工具类
 */
public class JDBCUtils3 {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //获得连接的方法
    public static Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        return conn;
    }

    //释放资源,重载
    public static void release(Statement stmt, Connection conn) {
        if(stmt != null) {
            try{
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null) {
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if(rs != null) {
            try{
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null) {
            try{
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null) {
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
}
