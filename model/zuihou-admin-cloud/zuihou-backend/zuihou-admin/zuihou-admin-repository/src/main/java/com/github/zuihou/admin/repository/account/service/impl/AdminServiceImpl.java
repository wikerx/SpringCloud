package com.github.zuihou.admin.repository.account.service.impl;

import com.github.zuihou.admin.entity.account.domain.AccountDO;
import com.github.zuihou.admin.entity.account.po.Admin;
import com.github.zuihou.admin.repository.account.dao.AdminMapper;
import com.github.zuihou.admin.repository.account.example.AdminExample;
import com.github.zuihou.admin.repository.account.service.AdminService;
import com.github.zuihou.base.dao.BaseAllDao;
import com.github.zuihou.base.service.impl.BaseAllServiceImpl;
import com.github.zuihou.commons.constant.DeleteStatus;
import com.github.zuihou.commons.context.CommonConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 11:13
 */
@Service
public class AdminServiceImpl extends BaseAllServiceImpl<Long, Admin, AdminExample> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstants.PW_ENCORDER_SALT);


    @Override
    protected BaseAllDao<Long, Admin, AdminExample> getDao() {
        return adminMapper;
    }

    @Override
    public Admin get(String userName, String passWord) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(userName).andIsDeleteEqualTo(DeleteStatus.UN_DELETE.getVal());
        Admin admin = adminMapper.selectEntity(example);
        if (admin == null || !encoder.matches(passWord, admin.getPassword())) {
            return null;
        }
        return admin;
    }

    @Override
    public AccountDO getAccount(String userName) {
        return adminMapper.getAccount(userName);
    }

    @Override
    public Admin save(Admin entity) {
        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);
        return super.save(entity);
    }

    @Override
    public Admin saveSelective(Admin entity) {
        String password = encoder.encode(entity.getPassword());
        entity.setPassword(password);
        return super.saveSelective(entity);
    }
}
