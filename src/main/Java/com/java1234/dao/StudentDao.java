package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;

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
     * @param : pageBean
     * @return : java.sql.ResultSet
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public ResultSet studentList(Connection con, PageBean pageBean, Student student, String bbirthday, String ebirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
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
    public int studentCount(Connection con, Student student, String bbirthday, String ebirthday) throws Exception {
        StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    /***
     * @Description : 删除
     * @Method_Name : studentDelete
     * @Param :  @param con
     * @param : delIds
     * @return : int
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public int studentDelete(Connection con, String delIds) throws Exception {
        String sql = "delete from t_student where stuId in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /***
     * @Description : 增加
     * @Method_Name : studentAdd
     * @Param :  @param con
     * @param : student
     * @return : int
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public int studentAdd(Connection con, Student student) throws Exception {
        String sql = "insert into t_student values(null,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        return pstmt.executeUpdate();
    }

    /***
     * @Description : 修改
     * @Method_Name : studentModify
     * @Param :  @param con
     * @param : student
     * @return : int
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public int studentModify(Connection con, Student student) throws Exception {
        String sql = "update t_student set stuNo=?,stuName=?,sex=?,birthday=?,gradeId=?,email=?,stuDesc=? where stuId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getGradeId());
        pstmt.setString(6, student.getEmail());
        pstmt.setString(7, student.getStuDesc());
        pstmt.setInt(8, student.getStuId());
        return pstmt.executeUpdate();
    }

    /***
     * @Description :
     * @Method_Name : getStudentByGradeId
     * @Param :  @param con
     * @param : gradeId
     * @return : boolean
     * @Creation Date : 2018/12/3
     * @Author : Sean
     */
    public boolean getStudentByGradeId(Connection con, String gradeId) throws Exception {
        String sql = "select * from t_student where gradeId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, gradeId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
