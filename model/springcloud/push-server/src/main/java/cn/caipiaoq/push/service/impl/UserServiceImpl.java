package cn.caipiaoq.push.service.impl;

import cn.caipiaoq.push.dao.UserRepository;
import cn.caipiaoq.push.entity.User;
import cn.caipiaoq.push.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Integer id) {
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByName(name);
    }

    @Override
    public User queryById(Integer id) {
        return userRepository.queryById(id);
    }

    @Override
    public User queryByName(String name) {
        return userRepository.queryByName(name);
    }

    @Override
    public List<User> findByNameAndDepartmentId(String name, Integer deparmentId) {
        return userRepository.findByNameAndDepartmentId(name, deparmentId);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike("%".concat(name).concat("%"));
    }

    @Override
    public List<User> findByNameNotLike(String name) {
        return userRepository.findByNameNotLike("%".concat(name).concat("%"));
    }

    @Override
    public List<User> findByName(String name, Pageable pageable) {
        Page<User> userPage = userRepository.findByName(name, pageable);
        return userPage.getContent();
    }

    @Override
    public List<User> findFirst10ByName(String name, Sort sort) {
        return userRepository.findFirst10ByName(name, sort);
    }

    @Override
    public List<User> findDistinctUserByNameOrDepartmentId(String name, Integer deparmentId) {
        return userRepository.findDistinctUserByNameOrDepartmentId(name, deparmentId);
    }

    @Override
    public List<User> findByCreateTimeBetween(LocalDate beginTime, LocalDate endTime) {
        ZonedDateTime beginZdt = beginTime.atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime endZdt = endTime.atStartOfDay(ZoneId.systemDefault());
        Date beginDate = Date.from(beginZdt.toInstant());
        Date endDate = Date.from(endZdt.toInstant());
        return userRepository.findByCreateTimeBetween(beginDate,endDate);
    }

    @Override
    public List<User> findByAgeLessThan(int age) {
        return userRepository.findByAgeLessThan(age);
    }

    @Override
    public List<User> findByAgeLessThanEqual(int age) {
        return userRepository.findByAgeLessThanEqual(age);
    }

    @Override
    public List<User> findByAgeGreaterThan(int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    @Override
    public List<User> findByAgeGreaterThanEqual(int age) {
        return userRepository.findByAgeGreaterThanEqual(age);
    }

    @Override
    public List<User> findByCreateTimeAfter(LocalDate createTime) {
        ZonedDateTime createTimeZdt = createTime.atStartOfDay(ZoneId.systemDefault());
        Date createDate = Date.from(createTimeZdt.toInstant());
        return userRepository.findByCreateTimeAfter(createDate);
    }

    @Override
    public List<User> findByCreateTimeBefore(LocalDate createTime) {
        ZonedDateTime createTimeZdt = createTime.atStartOfDay(ZoneId.systemDefault());
        Date createDate = Date.from(createTimeZdt.toInstant());
        return userRepository.findByCreateTimeBefore(createDate);
    }

    @Override
    public List<User> findByAgeIsNull() {
        return userRepository.findByAgeIsNull();
    }

    @Override
    public List<User> findByAgeNotNull() {
        return userRepository.findByAgeNotNull();
    }

    @Override
    public List<User> findByAgeIsNotNull() {
        return userRepository.findByAgeIsNotNull();
    }

    @Override
    public List<User> findByAgeOrderByNameDesc(int age) {
        return userRepository.findByAgeOrderByNameDesc(age);
    }

    @Override
    public List<User> findByAgeIn(Collection ages) {
        return userRepository.findByAgeIn(ages);
    }

    @Override
    public List<User> findByAgeNotIn(Collection ages) {
        return userRepository.findByAgeNotIn(ages);
    }

    @Override
    public User findUserQuery(String name, Integer departmentId) {
        return userRepository.findUserQuery(name, departmentId);
    }

    @Override
    public User findUserNativeQuery(String name, Integer departmentId) {
        return userRepository.findUserNativeQuery(name, departmentId);
    }

    @Override
    public User findUserQueryNameParam(String name, Integer departmentId) {
        return userRepository.findUserQueryNameParam(name, departmentId);
    }

    @Override
    public User findUserNativeQueryNameParam(String name, Integer departmentId) {
        return userRepository.findUserNativeQueryNameParam(name, departmentId);
    }

    @Override
    public List<Object[]> queryUserCount() {
        return userRepository.queryUserCount();
    }

    @Override
    public List<User> queryUsers(Integer departmentId, Pageable pageable) {
        Page<User> users = userRepository.queryUsers(departmentId, pageable);
        return users.getContent();
    }

    @Override
    public void updateName(String name, Integer id) {
        userRepository.updateName(name, id);
    }

}