package cn.caipiaoq.push.dao;

import cn.caipiaoq.push.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(Integer id);

    User findByName(String name);

    User getById(Integer id);

    User getByName(String name);

    User queryById(Integer id);

    User queryByName(String name);

    List<User> findByNameAndDepartmentId(String name, Integer deparmentId);

    List<User> findByNameLike(String name);

    List<User> findByNameNotLike(String name);

    Page<User> findByName(String name, Pageable pageable);

    List<User> findFirst10ByName(String name, Sort sort);

    List<User> findDistinctUserByNameOrDepartmentId(String name, Integer deparmentId);

    List<User> findByCreateTimeBetween(Date beginTime, Date endTime);

    List<User> findByAgeLessThan(int age);

    List<User> findByAgeLessThanEqual(int age);

    List<User> findByAgeGreaterThan(int age);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByCreateTimeAfter(Date createTime);

    List<User> findByCreateTimeBefore(Date createTime);

    List<User> findByAgeIsNull();

    List<User> findByAgeNotNull();

    List<User> findByAgeIsNotNull();

    List<User> findByAgeOrderByNameDesc(int age);

    List<User> findByAgeIn(Collection ages);

    List<User> findByAgeNotIn(Collection ages);

    //JPQL
    @Query("select u from User u where u.name=?1 and u.departmentId=?2")
    User findUserQuery(String name, Integer departmentId);

    //纯sql
    @Query(value = "select * from user where name=?1 and department_id=?2", nativeQuery = true)
    User findUserNativeQuery(String name, Integer departmentId);

    //纯JPQL 命名参数
    @Query("select u from User u where u.name=?1 and u.departmentId=?2")
    User findUserQueryNameParam(@Param("name") String name, @Param("departmentId") Integer departmentId);

    //纯sql 命名参数
    @Query(value = "select * from user where name=?1 and department_id=?2", nativeQuery = true)
    User findUserNativeQueryNameParam(@Param("name") String name, @Param("departmentId") Integer departmentId);

    @Query(value="select department_id,count(*) from user group by department_id", nativeQuery = true)
    List<Object[]> queryUserCount();

    //分页
    @Query("select u from User u where u.departmentId=?1")
    Page<User> queryUsers(@Param("departmentId") Integer departmentId, Pageable pageable);

    @Modifying
    @Query("update User u set u.name=?1 where u.id=?2")
    void updateName(String name, Integer id);
}
