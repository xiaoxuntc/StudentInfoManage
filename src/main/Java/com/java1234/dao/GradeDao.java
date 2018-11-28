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
}
