package com.example.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 * @date 2022/2/9 9:03 上午
 */
//  @WebFilter 标记一个类为filter，被spring进行扫描
//  urlPatterns：拦截规则，支持正则
@WebFilter(urlPatterns = {"/*"}, filterName = "LoginFilter")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器进入========");
    }

    //  控制chain.doFilter的方法的调用，来实现是否通过放行不放行
    //  web应用resp.sendRedirect("/index.html");
    //  场景：权限控制、用户登录(非前端后端分离场景)等
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if( true
                || httpServletRequest.getRequestURI().indexOf("/index")!=-1
                ||httpServletRequest.getRequestURI().indexOf("/login")!=-1
        ) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁========");
    }
}
