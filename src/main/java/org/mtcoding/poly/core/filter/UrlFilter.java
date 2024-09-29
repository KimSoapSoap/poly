package org.mtcoding.poly.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

public class UrlFilter implements Filter {

    private static final Pattern INVALID_URL_PATTERN = Pattern.compile("[^\\w?&=:/]|(?<!:)/{2,}");


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        String fullURL = requestURL.toString() + (queryString == null ? "" : "?" + queryString );

        if(INVALID_URL_PATTERN.matcher(fullURL).find()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write("\"{reason}\" : 잘못된 주소입니다.");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
