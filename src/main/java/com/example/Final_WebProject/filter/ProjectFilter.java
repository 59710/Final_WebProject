package com.example.Final_WebProject.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebFilter(filterName = "projectFilter", urlPatterns = "/*")

public class ProjectFilter implements Filter {

    //初始化ProjectFilter过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 打印过滤器初始化日志
        System.out.println("---------->> ProjectFilter init");
    }

    /**
     * 过滤器核心方法，用于拦截和处理HTTP请求
     *
     * @param request  Servlet请求对象，包含客户端的请求信息
     * @param response Servlet响应对象，用于向客户端发送响应
     * @param chain    过滤器链，用于调用下一个过滤器或目标资源
     * @throws IOException      当发生输入输出异常时抛出
     * @throws ServletException 当发生Servlet相关异常时抛出
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 打印过滤器执行日志
        System.out.println("---------->>  ProjectFilter doFilter");
        //过滤器，请求地址
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getServletPath();
        System.out.println("---------->> 过滤器，请求地址"+path);

        List<String> allowedPaths = Arrays.asList(
                "/59710/MainPage",
                "/59710/Failed",
                "/images/",        // 允许访问images目录
                "/css/",           // 允许访问css目录（如果有）
                "/js/",            // 允许访问js目录（如果有）
                "/favicon.ico"     // 允许访问网站图标
        );

        boolean shouldForwardToFailed = false;

        if (path.contains("/59710/")) {
            shouldForwardToFailed = true;

            // 检查是否在允许的路径中
            for (String allowedPath : allowedPaths) {
                if (path.contains(allowedPath)) {
                    shouldForwardToFailed = false;
                    break;
                }
            }
        }

        if (shouldForwardToFailed) {
            // 获取请求地址
            httpRequest.getRequestURI();
            // 转发到失败页面
            request.getRequestDispatcher("/59710/Failed").forward(request, response);
            // 打印失败日志
            System.out.println("---------->> 跳转失败页面");
        } else {
            // 允许请求继续
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 销毁ProjectFilter过滤器
        System.out.println("---------->> ProjectFilter destroy");
    }

}
