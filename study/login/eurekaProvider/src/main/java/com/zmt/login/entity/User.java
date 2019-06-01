package com.zmt.login.entity;

/**
 * @CLASSNAME :User
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/29  13:55
 * @Version :V1.0
 * @Status : 编写
 **/
public class User {
    private int id;
    private int age;
    private String name;
    private String account;
    private String password;
    private String tel;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
