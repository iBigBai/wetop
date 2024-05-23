package com.baidu.wetop.dao;

import com.baidu.wetop.pojo.NewsHeadline;
import com.baidu.wetop.pojo.vo.HeadlineDetailVo;
import com.baidu.wetop.pojo.vo.HeadlinePageVo;
import com.baidu.wetop.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {
    /**
     * 查询新闻列表
     *
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 查询新闻列表总条数
     *
     * @param headlineQueryVo
     * @return
     */
    int findTotalSize(HeadlineQueryVo headlineQueryVo);

    /**
     * 更新新闻的浏览量
     *
     * @param hid
     */
    void updatePageViews(Integer hid);

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
     * @return
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
     * @param hid
     * @return
     */
    int removeByHid(Integer hid);
}
