package cn.hzebin.jdbc.demo3;

import cn.hzebin.jdbc.utils.JDBCUtils;
import cn.hzebin.jdbc.utils.JDBCUtils3;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//使用连接池工具类
public class DataSourcesDemo2 {

    @Test
    public void demo1() {
        // 获得连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //获得连接
            conn = JDBCUtils3.getConnection();
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
            JDBCUtils3.release(rs, pstmt, conn);
        }
    }
}
