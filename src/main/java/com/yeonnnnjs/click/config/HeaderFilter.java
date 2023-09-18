package com.yeonnnnjs.click.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class HeaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String referer = httpServletRequest.getHeader("Referer");

        if (!isValidReferer(referer)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid");
            return;
        }

        chain.doFilter(httpServletRequest, response);
    }

    private boolean isValidReferer(String referer) {
        if (referer != null && referer.contains("localhost")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void destroy() {
    }
}
