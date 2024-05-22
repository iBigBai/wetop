package com.baidu.wetop.service.impl;

import com.baidu.wetop.dao.NewsHeadLineDao;
import com.baidu.wetop.dao.impl.NewsHeadlineDaoImpl;
import com.baidu.wetop.pojo.vo.HeadlinePageVo;
import com.baidu.wetop.pojo.vo.HeadlineQueryVo;
import com.baidu.wetop.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadLineDao newsHeadLineDao = new NewsHeadlineDaoImpl();

    /**
     * 查询新闻列表
     *
     * @param headlineQueryVo
     * @return
     */
    @Override
    public HashMap<String, Object> findNewsPage(HeadlineQueryVo headlineQueryVo) {
        HashMap<String, Object> pageInfo = new HashMap<>();
        List<HeadlinePageVo> pageData = newsHeadLineDao.findPageList(headlineQueryVo);
        //查询总页数
        int totalSize = newsHeadLineDao.findTotalSize(headlineQueryVo);
        Integer pageNum = headlineQueryVo.getPageNum();
        Integer pageSize = headlineQueryVo.getPageSize();
        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        pageInfo.put("pageData", pageData);
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalSize", totalSize);
        return pageInfo;
    }
}
