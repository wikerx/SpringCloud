package com.zmt.login.entity;

public class OrderTest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordertest.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordertest.orderNo
     *
     * @mbggenerated
     */
    private String orderno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordertest.orderName
     *
     * @mbggenerated
     */
    private String ordername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ordertest.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordertest.id
     *
     * @return the value of ordertest.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordertest.id
     *
     * @param id the value for ordertest.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordertest.orderNo
     *
     * @return the value of ordertest.orderNo
     *
     * @mbggenerated
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordertest.orderNo
     *
     * @param orderno the value for ordertest.orderNo
     *
     * @mbggenerated
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordertest.orderName
     *
     * @return the value of ordertest.orderName
     *
     * @mbggenerated
     */
    public String getOrdername() {
        return ordername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordertest.orderName
     *
     * @param ordername the value for ordertest.orderName
     *
     * @mbggenerated
     */
    public void setOrdername(String ordername) {
        this.ordername = ordername == null ? null : ordername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ordertest.remark
     *
     * @return the value of ordertest.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ordertest.remark
     *
     * @param remark the value for ordertest.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}