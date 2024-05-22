package com.baidu.wetop.dao;

import com.baidu.wetop.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    NewsUser findUserByName(String username);

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    NewsUser findByUid(Long uid);
}
