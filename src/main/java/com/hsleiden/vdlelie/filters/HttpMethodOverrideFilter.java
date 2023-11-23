package com.hsleiden.vdlelie.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class HttpMethodOverrideFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = httpRequest.getHeader("X-HTTP-Method-Override");
        if (method != null) {
            request = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getMethod() {
                    return method;
                }
            };
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
