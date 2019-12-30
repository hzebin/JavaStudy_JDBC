package cn.hzebin.jdbc.utils;

import java.sql.*;

/*
JDBC工具类
 */
public class JDBCUtils {
    //常量定义
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        driverClass = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/jdbctest";
        username = "root";
        password = "root1234";
    }

    //注册驱动方法
    public static void loadDriver() throws ClassNotFoundException {
        Class.forName(driverClass);
    }

    //获得连接数据库的方法
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        loadDriver();
        Connection conn = DriverManager.getConnection(url, username, password);
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
