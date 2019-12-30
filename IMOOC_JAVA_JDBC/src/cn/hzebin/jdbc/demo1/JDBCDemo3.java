package cn.hzebin.jdbc.demo1;

import cn.hzebin.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
/*
JDBC采用工具类
 */
public class JDBCDemo3 {
    @Test
    //保存记录
    public void demo1() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //获得连接
            conn = JDBCUtils.getConnection();
            //创建执行SQL语句的对象
            stmt = conn.createStatement();
            //编写SQL
            String sql = "INSERT INTO user VALUES(null, 'aaa', 'aaa', 'aaa')";
            //执行SQL
            int row = stmt.executeUpdate(sql);
            if(row > 0) {
                System.out.println("操作成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(stmt, conn);
        }
    }
}
