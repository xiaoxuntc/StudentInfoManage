package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.PageBean;

/***
 * @Description : 学生Dao
 * @Creation Date : 2018/12/2
 * @Author : Sean
 */
public class StudentDao {

    /***
     * @Description : 列表
     * @Method_Name : studentList
     * @Param :  @param con
     *  @param pageBean
     * @return : java.sql.ResultSet
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public ResultSet studentList(Connection con, PageBean pageBean) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /***
     * @Description : 计数
     * @Method_Name : studentCount
     * @Param :  @param con
     * @return : int
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public int studentCount(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }
}
