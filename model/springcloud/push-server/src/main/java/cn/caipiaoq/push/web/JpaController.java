package cn.caipiaoq.push.web;

import cn.caipiaoq.push.entity.User;
import cn.caipiaoq.push.service.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/jpa")
public class JpaController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(JpaController.class);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    UserService userService;

    @GetMapping("/user/add")
    public User add(@RequestBody User user){
        LOGGER.info("/user/add :{}", JSON.toJSONString(user));
        return userService.addUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public User delete(@PathVariable Integer id){
        LOGGER.info("/user/delete :{}", id);
        return userService.deleteUser(id);
    }

    @PutMapping("/user/update")
    public User update(@RequestBody User user){
        LOGGER.info("/user/update :{}", JSON.toJSONString(user));
        return userService.updateUser(user);
    }

    @GetMapping("/user/findById")
    public User findById(Integer id){
        LOGGER.info("/user/findById :{}", id);
        return userService.findById(id);
    }

    @GetMapping("/user/findByName")
    public User findByName(String name){
        LOGGER.info("/user/findByName :{}", name);
        return userService.findByName(name);
    }

    @GetMapping("/user/getById")
    public User getById(Integer id){
        LOGGER.info("/user/getById :{}", id);
        return userService.getById(id);
    }

    @GetMapping("/user/getByName")
    public User getByName(String name){
        LOGGER.info("/user/getByName :{}", name);
        return userService.getByName(name);
    }

    @GetMapping("/user/queryById")
    public User queryById(Integer id){
        LOGGER.info("/user/queryById :{}", id);
        return userService.queryById(id);
    }

    @GetMapping("/user/queryByName")
    public User queryByName(String name){
        LOGGER.info("/user/queryByName :{}", name);
        return userService.queryByName(name);
    }

    @GetMapping("/user/findByNameAndDepartmentId")
    public List<User> findByNameAndDepartmentId(String name, Integer departmentId){
        LOGGER.info("/user/findByNameAndDepartmentId :{},{}", name, departmentId);
        return userService.findByNameAndDepartmentId(name,departmentId);
    }

    @GetMapping("/user/findByNameLike")
    public List<User> findByNameLike(String name){
        LOGGER.info("/user/findByNameLike :{}", name);
        return userService.findByNameLike(name);
    }

    @GetMapping("/user/findByNameNotLike")
    public List<User> findByNameNotLike(String name){
        LOGGER.info("/user/findByNameNotLike :{}", name);
        return userService.findByNameNotLike(name);
    }

    @GetMapping("/user/findByNamePage")
    public List<User> findByNamePage(String name, int page, int size){
        LOGGER.info("/user/findByNamePage name:{},page:{},size:{}", name,page,size);
        PageRequest pageable = new PageRequest(page,size);
        return userService.findByName(name,pageable);
    }

    @GetMapping("/user/findFirst10ByName")
    public List<User> findFirst10ByName(String name){
        LOGGER.info("/user/findFirst10ByName name:{}", name);
        Sort sort = new Sort(Sort.Direction.DESC,"name");
        return userService.findFirst10ByName(name,sort);
    }

    @GetMapping("/user/findDistinctUserByNameOrDepartmentId")
    public List<User> findDistinctUserByNameOrDepartmentId(String name, Integer departmentId){
        LOGGER.info("/user/findDistinctUserByNameOrDepartmentId name:{},departmentId:{}", name,departmentId);
        return userService.findDistinctUserByNameOrDepartmentId(name,departmentId);
    }

    @GetMapping("/user/findByCreateTimeBetween")
    public List<User> findByCreateTimeBetween(String beginTime, String endTime){
        LOGGER.info("/user/findByCreateTimeBetween beginTime:{},endTime:{}", beginTime,endTime);
        LocalDate beginDate = LocalDate.parse(beginTime, formatter);
        LocalDate endDate = LocalDate.parse(endTime, formatter);
        return userService.findByCreateTimeBetween(beginDate,endDate);
    }

    @GetMapping("/user/findByAgeLessThan")
    public List<User> findByAgeLessThan(int age){
        LOGGER.info("/user/findByAgeLessThan age:{}", age);
        return userService.findByAgeLessThan(age);
    }

    @GetMapping("/user/findByAgeLessThanEqual")
    public List<User> findByAgeLessThanEqual(int age){
        LOGGER.info("/user/findByAgeLessThan age:{}", age);
        return userService.findByAgeLessThanEqual(age);
    }

    @GetMapping("/user/findByAgeGreaterThan")
    public List<User> findByAgeGreaterThan(int age){
        LOGGER.info("/user/findByAgeGreaterThan age:{}", age);
        return userService.findByAgeGreaterThan(age);
    }

    @GetMapping("/user/findByAgeGreaterThanEqual")
    public List<User> findByAgeGreaterThanEqual(int age){
        LOGGER.info("/user/findByAgeGreaterThanEqual age:{}", age);
        return userService.findByAgeGreaterThanEqual(age);
    }

    @GetMapping("/user/findByCreateTimeAfter")
    public List<User> findByCreateTimeAfter(String createTime){
        LOGGER.info("/user/findByAgeGreaterThanEqual createTime:{}", createTime);
        LocalDate createDate = LocalDate.parse(createTime, formatter);
        return userService.findByCreateTimeAfter(createDate);
    }

    @GetMapping("/user/findByCreateTimeBefore")
    public List<User> findByCreateTimeBefore(String createTime){
        LOGGER.info("/user/findByCreateTimeBefore createTime:{}", createTime);
        LocalDate createDate = LocalDate.parse(createTime, formatter);
        return userService.findByCreateTimeBefore(createDate);
    }

    @GetMapping("/user/findByAgeIsNull")
    public List<User> findByAgeIsNull(){
        LOGGER.info("/user/findByAgeIsNull");
        return userService.findByAgeIsNull();
    }

    @GetMapping("/user/findByAgeNotNull")
    public List<User> findByAgeNotNull(){
        LOGGER.info("/user/findByAgeNotNull");
        return userService.findByAgeNotNull();
    }

    @GetMapping("/user/findByAgeIsNotNull")
    public List<User> findByAgeIsNotNull(){
        LOGGER.info("/user/findByAgeIsNotNull");
        return userService.findByAgeIsNotNull();
    }

    @GetMapping("/user/findByAgeOrderByNameDesc")
    public List<User> findByAgeOrderByNameDesc(int age){
        LOGGER.info("/user/findByAgeGreaterThanEqual age:{}", age);
        return userService.findByAgeOrderByNameDesc(age);
    }

    @GetMapping("/user/findByAgeIn")
    public List<User> findByAgeIn(Collection ages){
        LOGGER.info("/user/findByAgeIn age:{}", ages);
        return userService.findByAgeIn(ages);
    }

    @GetMapping("/user/findByAgeNotIn")
    public List<User> findByAgeNotIn(Collection ages){
        LOGGER.info("/user/findByAgeNotIn age:{}", ages);
        return userService.findByAgeNotIn(ages);
    }

    @GetMapping("/user/findUserQuery")
    public User findUserQuery(String name, Integer departmentId){
        LOGGER.info("/user/findUserQuery age:{},departmentId", name, departmentId);
        return userService.findUserQuery(name,departmentId);
    }

    @GetMapping("/user/findUserNativeQuery")
    public User findUserNativeQuery(String name, Integer departmentId){
        LOGGER.info("/user/findUserNativeQuery age:{},departmentId", name, departmentId);
        return userService.findUserNativeQuery(name,departmentId);
    }

    @GetMapping("/user/findUserQueryNameParam")
    public User findUserQueryNameParam(String name, Integer departmentId){
        LOGGER.info("/user/findUserQueryNameParam age:{},departmentId", name, departmentId);
        return userService.findUserQueryNameParam(name,departmentId);
    }

    @GetMapping("/user/findUserNativeQueryNameParam")
    public User findUserNativeQueryNameParam(String name, Integer departmentId){
        LOGGER.info("/user/findUserNativeQueryNameParam age:{},departmentId", name, departmentId);
        return userService.findUserNativeQueryNameParam(name,departmentId);
    }

    @GetMapping("/user/queryUserCount")
    public List<Object[]> queryUserCount(){
        LOGGER.info("/user/queryUserCount");
        return userService.queryUserCount();
    }

    @GetMapping("/user/queryUsers")
    public List<User> queryUsers(Integer departmentId, int page, int size){
        LOGGER.info("/user/queryUsers departmentId:{},page:{},size:{}",departmentId, page, size);
        PageRequest pageable = new PageRequest(page,size);
        return userService.queryUsers(departmentId,pageable);
    }

    @PutMapping("/user/updateName")
    public User updateName(String name, Integer id){
        LOGGER.info("/user/updateName name:{},id:{}", name, id);
        userService.updateName(name,id);
        return userService.findById(id);
    }
}
