package cn.hzebin.jdbc.demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
添加操作
 */
public class JDBCDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest", "root", "root1234");
            stmt = conn.createStatement();
            String sql = "INSERT INTO user VALUES(null, 'cwl', '1234', '陈文略')";
            int row = stmt.executeUpdate(sql);
            if(row > 0) {
                System.out.println("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
}
