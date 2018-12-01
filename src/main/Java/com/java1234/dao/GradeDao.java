package com.java1234.dao;

import com.java1234.model.Grade;
import com.java1234.model.PageBean;
import com.java1234.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/***
 * @Description : 年级Dao
 * @Creation Date : 2018/11/24
 * @Author : Sean
 */
public class GradeDao {

    /***
     * @Description :
     * @Method_Name : gradeList
     * @Param :  @param con
     *  @param pageBean
     *  @param grade
     * @return : java.sql.ResultSet
     * @Creation Date : 2018/12/1
     * @Author : Sean
     */
    public ResultSet gradeList(Connection con, PageBean pageBean, Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_grade");
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /***
     * @Description :
     * @Method_Name : gradeCount
     * @Param :  @param con
     * @param grade
     * @return : int
     * @Creation Date : 2018/12/1
     * @Author : Sean
     */
    public int gradeCount(Connection con, Grade grade) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
        if (StringUtil.isNotEmpty(grade.getGradeName())) {
            sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    /**
     * delete from tableName where field in (1,3,5)
     *
     * @param con
     * @param delIds
     * @return
     * @throws Exception
     */
    public int gradeDelete(Connection con, String delIds) throws Exception {
        String sql = "delete from t_grade where id in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /***
     * @Description :
     * @Method_Name : gradeAdd
     * @Param :  @param con
     * @param grade
     * @return : int
     * @Creation Date : 2018/12/1
     * @Author : Sean
     */
    public int gradeAdd(Connection con, Grade grade) throws Exception {
        String sql = "insert into t_grade values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        return pstmt.executeUpdate();
    }

    /***
     * @Description :
     * @Method_Name : gradeModify
     * @Param :  @param con
     * @param grade
     * @return : int
     * @Creation Date : 2018/12/1
     * @Author : Sean
     */
    public int gradeModify(Connection con, Grade grade) throws Exception {
        String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getGradeName());
        pstmt.setString(2, grade.getGradeDesc());
        pstmt.setInt(3, grade.getId());
        return pstmt.executeUpdate();
    }
}
