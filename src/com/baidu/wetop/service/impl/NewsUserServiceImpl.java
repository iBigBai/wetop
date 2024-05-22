package com.baidu.wetop.service.impl;

import com.baidu.wetop.dao.NewsUserDao;
import com.baidu.wetop.dao.impl.NewsUserDaoImpl;
import com.baidu.wetop.pojo.NewsUser;
import com.baidu.wetop.service.NewsUserService;
import com.baidu.wetop.utils.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private final NewsUserDao newsUserDao = new NewsUserDaoImpl();

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public NewsUser findUserByName(String username) {
        return newsUserDao.findUserByName(username);
    }

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    @Override
    public NewsUser findByUid(Long uid) {
        return newsUserDao.findByUid(uid);
    }

    /**
     * 注册
     *
     * @param newsUser
     */
    @Override
    public int regist(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        return newsUserDao.insertNewsUser(newsUser);
    }
}
