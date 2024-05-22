package com.baidu.wetop.service.impl;

import com.baidu.wetop.dao.NewsTypeDao;
import com.baidu.wetop.dao.impl.NewsTypeDaoImpl;
import com.baidu.wetop.pojo.NewsType;
import com.baidu.wetop.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private final NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();

    /**
     * 查询所有新闻类型
     *
     * @return
     */
    @Override
    public List<NewsType> fandAllTypes() {
        return newsTypeDao.fandAllTypes();
    }
}
