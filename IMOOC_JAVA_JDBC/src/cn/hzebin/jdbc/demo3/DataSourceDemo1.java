package cn.hzebin.jdbc.demo3;

import cn.hzebin.jdbc.utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
连接池测试类
 */
public class DataSourceDemo1 {
    @Test
    //手动设置连接池
    public void demo1() {
        // 获得连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 创建连接池
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            //设置连接池的参数
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbctest");;
            dataSource.setUser("root");
            dataSource.setPassword("root1234");
            dataSource.setMaxPoolSize(20);  //最大连接数
            dataSource.setInitialPoolSize(3);  //初始化时的连接数

            //获得连接
            conn = dataSource.getConnection();
            // 编写SQL语句
            String sql = "SELECT * FROM user";
            // 预编译SQL语句
            pstmt = conn.prepareStatement(sql);
            //设置参数
            //执行SQL
            rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
    }

    //使用配置文件的方式创建连接池,查找src路径下的
    @Test
    public void demo2() {
        // 获得连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 创建连接池，会默认查找c3p0配置文件
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            //获得连接
            conn = dataSource.getConnection();
            // 编写SQL语句
            String sql = "SELECT * FROM user";
            // 预编译SQL语句
            pstmt = conn.prepareStatement(sql);
            //设置参数
            //执行SQL
            rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("uid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
    }
}
