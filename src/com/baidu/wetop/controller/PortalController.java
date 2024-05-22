package com.baidu.wetop.controller;

import com.baidu.wetop.common.Result;
import com.baidu.wetop.pojo.NewsType;
import com.baidu.wetop.pojo.vo.HeadlineQueryVo;
import com.baidu.wetop.service.NewsHeadlineService;
import com.baidu.wetop.service.NewsTypeService;
import com.baidu.wetop.service.impl.NewsHeadlineServiceImpl;
import com.baidu.wetop.service.impl.NewsTypeServiceImpl;
import com.baidu.wetop.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private final NewsTypeService newsTypeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();

    /**
     * 查询所有新闻类型
     *
     * @param req
     * @param resp
     */
    public void findAllTypes(HttpServletRequest req, HttpServletResponse resp) {
        List<NewsType> newsTypeList = newsTypeService.fandAllTypes();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }

    /**
     * 查询新闻分页
     *
     * @param req
     * @param resp
     */

    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) {
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        //分页5项数据
        HashMap<String, Object> pageInfo = headlineService.findNewsPage(headlineQueryVo);
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageInfo", pageInfo);
        WebUtil.writeJson(resp, Result.ok(data));
    }
}
