package org.mtcoding.poly.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class UrlFilter implements Filter {

    private static final Pattern INVALID_URL_PATTERN = Pattern.compile("[^\\w?&=:/]|(?<!:)/{2,}");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        if(INVALID_URL_PATTERN.matcher(requestURI).find()) {
      /*      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write("\"{reason}\" : 잘못된 주소입니다.");
            System.out.println("잘못됐어");
            return;*/
        }

        System.out.println("잘 들어왔어");
        filterChain.doFilter(request, response);

    }
}
