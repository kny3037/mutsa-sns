package com.mutsasnskimnayeong.configuration.filter;

import com.mutsasnskimnayeong.domain.entity.User;
import com.mutsasnskimnayeong.exceptions.ErrorCode;
import com.mutsasnskimnayeong.service.UserService;
import com.mutsasnskimnayeong.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);


        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;

        try {
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.info("token 추출에 실패 했습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        if (JwtTokenUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        String userName = JwtTokenUtil.getUserName(token,secretKey);
        log.info("userName:{}", userName);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
