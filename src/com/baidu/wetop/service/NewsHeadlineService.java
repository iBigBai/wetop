package com.baidu.wetop.service;

import com.baidu.wetop.pojo.NewsHeadline;
import com.baidu.wetop.pojo.vo.HeadlineDetailVo;
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

    /**
     * 查询新闻详情
     *
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(Integer hid);

    /**
     * 添加新闻
     *
     * @param newsHeadline
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    /**
     * 根据hid查询新闻
     *
     * @param hid
     * @return
     */
    NewsHeadline findHeadlineByHid(String hid);

    /**
     * 更新新闻
     *
     * @param newsHeadline
     * @return
     */
    int updateHeadline(NewsHeadline newsHeadline);

    /**
     * 删除新闻
     *
     * @param hid
     * @return
     */
    int removeByHid(Integer hid);
}
