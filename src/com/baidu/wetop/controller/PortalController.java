package com.baidu.wetop.controller;

import com.baidu.wetop.common.Result;
import com.baidu.wetop.pojo.NewsType;
import com.baidu.wetop.service.NewsTypeService;
import com.baidu.wetop.service.impl.NewsTypeServiceImpl;
import com.baidu.wetop.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private final NewsTypeService newsTypeService = new NewsTypeServiceImpl();

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
}
