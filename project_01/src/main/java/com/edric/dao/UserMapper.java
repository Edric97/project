package com.edric.dao;

import com.edric.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 根据用户名查询用户是否存在
     * @param username
     * @return 返回null说明用户不存在，否则直接查出用户名
     */
    User queryUserByUsername(@Param("username") String username);

    /**
     * 根据用户名和密码查询用户
     * 这里一定要给参数加上 @Param 注解，不然会出现 BindingException
     * @param username
     * @param password
     * @return
     */
    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    int saveUser(User user);



}
