package com.baidu.wetop.controller;

import com.baidu.wetop.common.Result;
import com.baidu.wetop.common.ResultCodeEnum;
import com.baidu.wetop.pojo.NewsUser;
import com.baidu.wetop.service.NewsUserService;
import com.baidu.wetop.service.impl.NewsUserServiceImpl;
import com.baidu.wetop.utils.JwtHelper;
import com.baidu.wetop.utils.MD5Util;
import com.baidu.wetop.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private final NewsUserService newsUserService = new NewsUserServiceImpl();

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser loginUser = newsUserService.findUserByName(newsUser.getUsername());
        Result result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        //判断用户名
        if (null != loginUser) {
            //判断密码
            if (loginUser.getUserPwd().equals(MD5Util.encrypt(newsUser.getUserPwd()))) {
                String token = JwtHelper.createToken(loginUser.getUid().longValue());
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                result = Result.ok(data);
            } else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 获取用户信息
     *
     * @param req
     * @param resp
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        if (null != token && !JwtHelper.isExpiration(token)) {
            Long uid = JwtHelper.getUserId(token);
            NewsUser newsUser = newsUserService.findByUid(uid);
            newsUser.setUserPwd("***");
            result = Result.ok(newsUser);
        }
        WebUtil.writeJson(resp, result);
    }
}
