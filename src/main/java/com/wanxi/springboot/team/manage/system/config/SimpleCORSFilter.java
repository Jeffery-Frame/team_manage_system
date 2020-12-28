//package com.wanxi.springboot.team.manage.system.config;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//
//public class SimpleCORSFilter implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//
//        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//
//        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
//
//        res.setHeader("Access-Control-Max-Age", "3600");
//
//        res.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
//
//        filterChain.doFilter(req, res);
//    }
//    public void init(FilterConfig filterConfig) {}
//
//    public void destroy() {}
//
//}
