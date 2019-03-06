package com.scott.study.entity;

/**
 * @CLASSNAME :Dept
 * @Description :Dept
 * @Author :Mr.薛
 * @Data :2019/2/26 0026  17:49
 * @Version :V1.0
 * @Status : 编写
 **/
public class Dept {
    private Long deptno;
    private String dname;
    private String loc;

//    get   set

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
