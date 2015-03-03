package fr.cvlaminck.notidroid.cloud.web.filters;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple Filter allowing Access-Control-Allow-Origin request.
 * Class created by idursun : https://github.com/idursun/spring-and-angular.git
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9000"); //FIXME: Make it easily configurable for deployment.
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, x-device-id");
        }
        filterChain.doFilter(request, response);
    }

}
