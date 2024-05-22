package com.baidu.wetop.service;

import com.baidu.wetop.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有新闻类型
     *
     * @return
     */
    List<NewsType> fandAllTypes();
}
