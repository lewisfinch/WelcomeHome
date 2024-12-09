package com.wh.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wh.pojo.Result;
import com.wh.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        log.info("Request URL : " + url);

        String jwt = request.getHeader("token");

        if(jwt== null||jwt.isEmpty()){return true;}

        if(!StringUtils.hasLength(jwt)){
            log.info("JWT is empty");
            Result error = Result.error("NOT_LOGIN");
            ObjectMapper objectMapper = new ObjectMapper();
            String notLogin = objectMapper.writeValueAsString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e){
            e.printStackTrace();
            log.info("JWT parse error");
            Result error = Result.error("NOT_LOGIN");
            ObjectMapper objectMapper = new ObjectMapper();
            String notLogin = objectMapper.writeValueAsString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("JWT parse success");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
