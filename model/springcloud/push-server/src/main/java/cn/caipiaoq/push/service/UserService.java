package cn.caipiaoq.push.service;

import cn.caipiaoq.push.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserService {
    User addUser(User user);

    User deleteUser(Integer id);

    User updateUser(User user);

    User findById(Integer id);

    User findByName(String name);

    User getById(Integer id);

    User getByName(String name);

    User queryById(Integer id);

    User queryByName(String name);

    List<User> findByNameAndDepartmentId(String name, Integer deparmentId);

    List<User> findByNameLike(String name);

    List<User> findByNameNotLike(String name);

    List<User> findByName(String name, Pageable pageable);

    List<User> findFirst10ByName(String name, Sort sort);

    List<User> findDistinctUserByNameOrDepartmentId(String name, Integer deparmentId);

    List<User> findByCreateTimeBetween(LocalDate beginTime, LocalDate endTime);

    List<User> findByAgeLessThan(int age);

    List<User> findByAgeLessThanEqual(int age);

    List<User> findByAgeGreaterThan(int age);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByCreateTimeAfter(LocalDate createTime);

    List<User> findByCreateTimeBefore(LocalDate createTime);

    List<User> findByAgeIsNull();

    List<User> findByAgeNotNull();

    List<User> findByAgeIsNotNull();

    List<User> findByAgeOrderByNameDesc(int age);

    List<User> findByAgeIn(Collection ages);

    List<User> findByAgeNotIn(Collection ages);

    User findUserQuery(String name, Integer departmentId);

    User findUserNativeQuery(String name, Integer departmentId);

    User findUserQueryNameParam(String name, Integer departmentId);

    User findUserNativeQueryNameParam(String name, Integer departmentId);

    List<Object[]> queryUserCount();

    List<User> queryUsers(Integer departmentId, Pageable pageable);

    void updateName(String name, Integer id);
}