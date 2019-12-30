package cn.hzebin.jdbc.demo2;

import cn.hzebin.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//解决SQL注入漏洞的的问题
public class JDBCDemo6 {
    public static void main(String[] args) {
        //boolean flag = login("hzb", "1234");  //正常登录
        boolean flag = login("hzb' or '1 = 1", "asadsadsadsasa");  //注入失败
        //boolean flag = login("hzb' -- ", "asadsadsadsasa");  //注入失败
        if(flag) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }

    public static boolean login(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;  //！！
        ResultSet rs = null;
        boolean flag = false;
        try {
            // 获得连接
            conn = JDBCUtils.getConnection();
            // 编写SQL语句
            String sql = "SELECT * FROM user WHERE USERNAME = ? AND PASSWORD = ?";
            // 预处理SQL
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 执行SQL
            rs = pstmt.executeQuery();
            //判断结果
            if(rs.next()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
        return flag;
    }
}
