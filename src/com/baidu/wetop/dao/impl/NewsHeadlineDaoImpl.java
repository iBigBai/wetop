package com.baidu.wetop.dao.impl;

import com.baidu.wetop.dao.BaseDao;
import com.baidu.wetop.dao.NewsHeadLineDao;
import com.baidu.wetop.pojo.vo.HeadlinePageVo;
import com.baidu.wetop.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadLineDao {
    /**
     * 查询新闻列表
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        String sql = "select hid, title, type, page_views as pageViews, TIMESTAMPDIFF(HOUR,create_time,NOW()) as pastHours, publisher from news_headline where is_deleted = 0 ";
        List<Object> params = new ArrayList<>();
        //关键词 模糊查询
        String keyWords = headlineQueryVo.getKeyWords();
        if (null != keyWords && keyWords.length() > 0) {
            sql = sql.concat("and title like ? ");
            params.add("%" + keyWords + "%");
        }
        //新闻类型
        Integer type = headlineQueryVo.getType();
        if (null != type && type != 0) {
            sql = sql.concat("and type = ? ");
            params.add(type);
        }
        //排序
        sql = sql.concat("order by pastHours, page_views desc ");
        sql = sql.concat("limit ? , ? ");
        params.add((headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());
        return baseQuery(HeadlinePageVo.class, sql, params.toArray());
    }

    /**
     * 查询新闻列表总条数
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public int findTotalSize(HeadlineQueryVo headlineQueryVo) {
        String sql = "select count(1) from news_headline where is_deleted = 0 ";
        List<Object> params = new ArrayList<>();
        String keyWords = headlineQueryVo.getKeyWords();
        if (null != keyWords && keyWords.length() > 0) {
            sql = sql.concat("and title like ? ");
            params.add("%" + keyWords + "%");
        }
        //新闻类型
        Integer type = headlineQueryVo.getType();
        if (null != type && type != 0) {
            sql = sql.concat("and type = ? ");
            params.add(type);
        }
        return baseQueryObject(Long.class, sql, params.toArray()).intValue();
    }
}
