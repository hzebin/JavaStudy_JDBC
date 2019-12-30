package cn.hzebin.jdbc.demo1;

import org.junit.Test;

import java.sql.*;
/*
查询操作
 */
public class JDBCDemo1 {

    @Test
    public void demo1() {
        // 先申明对象
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            // 加载驱动
            //DriverManager.registerDriver(new Driver()); 会导致驱动注册两次，底层的静态代码块已经注册了
            Class.forName("com.mysql.jdbc.Driver");

            // 获得连接  jdbc:mysql://MySQL服务器ip地址:端口号/数据库名 用户名 密码
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "root1234");

            // 创建执行sql语句的对象
            String sql = "SELECT * FROM user";
            stmt = conn.createStatement();

            // 执行sql语句
            resultSet = stmt.executeQuery(sql);

            // 输出结果集
            while (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                System.out.println(uid + " " + username + " " + password + " " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;  //设置为null，垃圾回收机制会更早回收对象
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
    }

}
