package com.baidu.wetop.dao.impl;

import com.baidu.wetop.dao.BaseDao;
import com.baidu.wetop.dao.NewsHeadLineDao;
import com.baidu.wetop.pojo.NewsHeadline;
import com.baidu.wetop.pojo.vo.HeadlineDetailVo;
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

    /**
     * 更新新闻的浏览量
     *
     * @param hid
     */
    @Override
    public void updatePageViews(Integer hid) {
        String sql = "update news_headline set page_views = page_views + 1 where hid = ?";
        baseUpdate(sql, hid);
    }

    /**
     * 查询新闻详情
     *
     * @param hid
     * @return
     */
    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        String sql = "select h.hid as hid, h.title as title, h.article, h.type,t.tname as typeName, h.page_views as pageViews, timestampdiff(hour,h.create_time,now()) as pastHours, h.publisher as publisher, u.nick_name as author  from news_headline as h left join news_type as t on h.type = t.tid left join news_user as u on h.publisher = u.uid where h.hid = ?";
        List<HeadlineDetailVo> headlineDetailVoList = baseQuery(HeadlineDetailVo.class, sql, hid);
        return headlineDetailVoList != null && headlineDetailVoList.size() > 0 ? headlineDetailVoList.get(0) : null;
    }

    /**
     * 添加新闻
     *
     * @param newsHeadline
     * @return
     */
    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "insert into news_headline values(DEFAULT,?,?,?,?,0,NOW(),NOW(),0)";
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());
    }

    @Override
    public NewsHeadline findHeadlineByHid(String hid) {
        String sql = "select hid, title, article, type from news_headline where hid = ? ";
        List<NewsHeadline> newsHeadlineList = baseQuery(NewsHeadline.class, sql, hid);
        return newsHeadlineList != null && newsHeadlineList.size() > 0 ? newsHeadlineList.get(0) : null;
    }

    /**
     * 更新新闻
     *
     * @param newsHeadline
     * @return
     */
    @Override
    public int updateHeadline(NewsHeadline newsHeadline) {
        String sql = "update news_headline set title = ?, article= ? , type =? , update_time = NOW() where hid = ? ";
        return baseUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getHid());
    }

    /**
     * 删除新闻
     *
     * @param hid
     * @return
     */
    @Override
    public int removeByHid(Integer hid) {
        String sql = "update news_headline set is_deleted =1 ,  update_time =NOW() where hid = ? ";
        return baseUpdate(sql, hid);
    }
}
