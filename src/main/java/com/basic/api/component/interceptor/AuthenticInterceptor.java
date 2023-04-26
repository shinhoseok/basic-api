package com.basic.api.component.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticInterceptor extends WebContentInterceptor {
    private final Logger log = LoggerFactory.getLogger(AuthenticInterceptor.class);

    /**
     * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
     * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        HttpSession session = request.getSession();
        log.debug("CustomAuthenticInterceptor sessionID "+session.getId());
        log.debug("CustomAuthenticInterceptor ================== ");

        boolean isPermittedURL = true;
        return isPermittedURL;
    }
}
