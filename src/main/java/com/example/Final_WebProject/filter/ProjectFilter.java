//package com.example.Final_WebProject.filter;
//
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//
//@WebFilter(filterName = "projectFilter", urlPatterns = "/*")
//
//public class ProjectFilter implements Filter {
//
//    //初始化ProjectFilter过滤器
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // 打印过滤器初始化日志
//        System.out.println("---------->> ProjectFilter init");
//    }
//
//    /**
//     * 过滤器核心方法，用于拦截和处理HTTP请求
//     *
//     * @param request  Servlet请求对象，包含客户端的请求信息
//     * @param response Servlet响应对象，用于向客户端发送响应
//     * @param chain    过滤器链，用于调用下一个过滤器或目标资源
//     * @throws IOException      当发生输入输出异常时抛出
//     * @throws ServletException 当发生Servlet相关异常时抛出
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String path = req.getServletPath();
//        if (path.startsWith("/static") || path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/img")) {
//            System.out.println("---------->> ProjectFilter doFilter");
//            chain.doFilter(request, response);
//        }
//        else {
//            // 获取请求的URL路径
//            String url = req.getRequestURI();
//            // 定义需要处理的URL路径列表
//            List<String> urls = Arrays.asList("/login", "/register", "/Failed");
//            // 判断当前请求的URL是否需要处理
//            if (urls.contains(url)) {
//                System.out.println("---------->> ProjectFilter doFilter");
//                // 处理登录、注册和错误页面的请求
//                chain.doFilter(request, response);
//                return ;
//            } else {
//                // 获取Session对象
//                HttpServletRequest req1 = (HttpServletRequest) request;
//                HttpSession session = req1.getSession();
//            }
//        }
//        chain.doFilter(request, response);
//        System.out.println("---------->> ProjectFilter doFilter");
//    }
//
//    @Override
//    public void destroy() {
//        // 销毁ProjectFilter过滤器
//        System.out.println("---------->> ProjectFilter destroy");
//    }
//
//}
