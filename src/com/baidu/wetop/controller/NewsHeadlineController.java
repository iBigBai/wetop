package com.baidu.wetop.controller;

import com.baidu.wetop.common.Result;
import com.baidu.wetop.pojo.NewsHeadline;
import com.baidu.wetop.service.NewsHeadlineService;
import com.baidu.wetop.service.impl.NewsHeadlineServiceImpl;
import com.baidu.wetop.utils.JwtHelper;
import com.baidu.wetop.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    private NewsHeadlineService newsHeadlineService = new NewsHeadlineServiceImpl();

    /**
     * 发布新闻
     *
     * @param req
     * @param resp
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        //获取用户ID
        Long userId = JwtHelper.getUserId(req.getHeader("token"));
        newsHeadline.setPublisher(userId.intValue());
        newsHeadlineService.addNewsHeadline(newsHeadline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 新闻回显
     *
     * @param req
     * @param resp
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) {
        String hid = req.getParameter("hid");
        NewsHeadline newsHeadline = newsHeadlineService.findHeadlineByHid(hid);
        HashMap<String, NewsHeadline> data = new HashMap<>();
        data.put("headline", newsHeadline);
        WebUtil.writeJson(resp, Result.ok(data));
    }

    /**
     * 保存新闻修改
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        newsHeadlineService.updateHeadline(newsHeadline);
        WebUtil.writeJson(resp, Result.ok(null));
    }

    /**
     * 删除新闻
     *
     * @param req
     * @param resp
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        newsHeadlineService.removeByHid(hid);
        WebUtil.writeJson(resp, Result.ok(null));
    }
}
