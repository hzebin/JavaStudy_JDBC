package cn.hzebin.jdbc.demo2;

import cn.hzebin.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo5 {
    @Test
    // 测试SQL注入漏洞
    public void demo1() {
        JDBCDemo5 a = new JDBCDemo5();
        //boolean flag = a.login("hzb", "1234");  //正常登录
        //boolean flag = a.login("hzb' or '1 = 1", "asadsadsadsasa");  //注入成功
        boolean flag = a.login("hzb' -- ", "asadsadsadsasa");  //注入成功
        if(flag) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }

    // 测试SQL语句注入的漏洞
    public boolean login(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM user WHERE username = '" + username + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return flag;
    }
}
