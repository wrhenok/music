package com.kaifamiao.music.service;

import com.kaifamiao.music.domain.User;

public interface UserService {

    User selectLogin(User user);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}


