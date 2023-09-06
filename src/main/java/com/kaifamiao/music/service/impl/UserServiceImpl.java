package com.kaifamiao.music.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.kaifamiao.music.mapper.UserMapper;
import com.kaifamiao.music.domain.User;
import com.kaifamiao.music.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selectLogin(User user) {
        return userMapper.selectByLogin(user);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}


