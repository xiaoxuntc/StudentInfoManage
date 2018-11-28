package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;

/***
 * @Description : 数据库工具类
 * @Creation Date : 2018/11/17
 * @Author : Sean
 */
public class DbUtil {

    private String dbUrl = "jdbc:mysql://localhost:3306/db_studentInfo";
    private String dbUserName = "root";
    private String dbPassword = "root";
    private String jdbcName = "com.mysql.jdbc.Driver";

    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
