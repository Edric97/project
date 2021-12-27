package com.edric.service;

import com.edric.pojo.User;

public interface UserService {

    int findUserId(String username, String password);

    // 注册
    void registerUser(User user);

    // 登陆
    User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示已经存在，false则不存在（可用）
     */
    boolean existsUsername(String username);

}
