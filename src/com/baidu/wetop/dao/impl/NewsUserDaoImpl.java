package com.baidu.wetop.dao.impl;

import com.baidu.wetop.dao.BaseDao;
import com.baidu.wetop.dao.NewsUserDao;
import com.baidu.wetop.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public NewsUser findUserByName(String username) {
        String sql = "select uid, username, user_pwd as userPwd, nick_name as nickName from news_user where username = ?";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        return null != newsUserList && newsUserList.size() > 0 ? newsUserList.get(0) : null;
    }

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    @Override
    public NewsUser findByUid(Long uid) {
        String sql = "select uid,username,user_pwd as userPwd ,nick_name as nickName from news_user where uid = ?";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, uid);
        return null != newsUserList && newsUserList.size() > 0 ? newsUserList.get(0) : null;
    }

    /**
     * 插入用户
     *
     * @param newsUser
     * @return
     */
    @Override
    public int insertNewsUser(NewsUser newsUser) {
        String sql = "insert into news_user values(DEFAULT,?,?,?)";
        return baseUpdate(sql, newsUser.getUsername(), newsUser.getUserPwd(), newsUser.getNickName());
    }
}
