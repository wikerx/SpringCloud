package com.model;

public class InterfaceBeanVO {

    private String beanId = "";//spring实例化的ID
    private String beanClass = "";//spring实例化bean的路径
    private String interfacecode = "";//请求接口代码

    public InterfaceBeanVO(String beanid, String beanclass, String interfacecode) {
        this.beanId = beanid;
        this.beanClass = beanclass;
        this.interfacecode = interfacecode;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getInterfacecode() {
        return interfacecode;
    }

    public void setInterfacecode(String interfacecode) {
        this.interfacecode = interfacecode;
    }
}
