package com.baidu.wetop.dao;

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
}
