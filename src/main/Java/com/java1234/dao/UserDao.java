package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.User;

/***
 * @Description : 用户Dao
 * @Creation Date : 2018/11/17
 * @Author : Sean
 */
public class UserDao {

    /***
     * @Description : 登陆验证
     * @Method_Name : login
     * @Param :  @param con
     * @param user
     * @return : com.java1234.model.User
     * @Creation Date : 2018/11/18
     * @Author : Sean
     */
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from t_user where userName=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setUserName(rs.getString("userName"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}
