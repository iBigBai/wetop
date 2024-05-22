package com.baidu.wetop.dao.impl;

import com.baidu.wetop.dao.BaseDao;
import com.baidu.wetop.dao.NewsTypeDao;
import com.baidu.wetop.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> fandAllTypes() {
        String sql = "select tid,tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
