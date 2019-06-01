package com.zmt.login.mapper;

import com.zmt.login.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME :UserDao
 * @Description :DOTO
 * @Author :Mr.薛
 * @Data :2019/3/29  13:57
 * @Version :V1.0
 * @Status : 编写
 **/
@Mapper
public interface UserDao {
    /*查询用户信息，根据用户账户*/
    @Select("select * from user where account = #{account}")
    User selectByAccount(@Param("account") String account);

    /*添加用户信息*/
    @Transactional
    @Modifying
    @Insert("insert into user(age,name,account,password,tel,email) values(#{age},#{name},#{account},#{password},#{tel},#{email})")
    int addUser(@Param("age")int age,@Param("name") String name,@Param("account")String account,
                @Param("password")String password,@Param("tel")String tel,@Param("email")String email);
}
