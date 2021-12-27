package com.edric.service.impl;

import com.edric.dao.UserMapper;
import com.edric.pojo.User;
import com.edric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(User user) {
        if (userMapper.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            userMapper.saveUser(user);
        }
    }

    @Override
    public User login(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userMapper.queryUserByUsername(username) == null ? false : true;
    }
}
