package com.java1234.model;

/***
 * @Description : 分页
 * @Creation Date : 2018/11/24
 * @Author : Sean
 */
public class PageBean {
    /**
     * 第几页
     */
    private int page;
    /**
     * 每页记录数
     */
    private int rows;
    /**
     * 起始页
     */
    private int start;

    public PageBean(int page, int rows) {
        super();
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return (page - 1) * rows;
    }


}
