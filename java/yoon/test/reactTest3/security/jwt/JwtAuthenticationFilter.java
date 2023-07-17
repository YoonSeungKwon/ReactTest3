package yoon.test.reactTest3.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accToken = jwtProvider.resolveAccessToken(request);
        if(accToken!=null && jwtProvider.validateToken(accToken)){
            Authentication authentication = jwtProvider.getAuth(accToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        String refToken = jwtProvider.resolveRefreshToken(request);
        if(!jwtProvider.validateToken(accToken) && jwtProvider.validateToken(refToken)){
            jwtProvider.createAccessToken(accToken);
            response.setHeader("Authorization", accToken);
        }
        filterChain.doFilter(request, response);
    }
}
