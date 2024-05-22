package com.baidu.wetop.service;

import com.baidu.wetop.pojo.vo.HeadlineQueryVo;

import java.util.HashMap;

public interface NewsHeadlineService {
    /**
     * 查询新闻列表
     *
     * @param headlineQueryVo
     * @return
     */
    HashMap<String, Object> findNewsPage(HeadlineQueryVo headlineQueryVo);
}
