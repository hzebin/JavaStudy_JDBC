package cn.hzebin.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
加载配置文件来编写JDBC工具类
 */
public class JDBCUtils2 {
    //常量定义
    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        //加载属性文件并解析
        Properties props = new Properties();
        //使用类的加载器加载配置文件jdbc.properties
        InputStream is = JDBCUtils2.class.getClassLoader().getResourceAsStream("cn/hzebin/jdbc/jdbc.properties");
        try{
            props.load(is);
        } catch(IOException e) {
            e.printStackTrace();
        }

        driverClass = props.getProperty("driverClass");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
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
