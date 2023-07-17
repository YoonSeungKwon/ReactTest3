package yoon.test.reactTest3.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.test.reactTest3.domain.Members;
import yoon.test.reactTest3.service.MemberService;
import yoon.test.reactTest3.vo.response.MemberResponse;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final MemberService memberService;

    private String SECRET_KEY = "98YOONSK12YOONSK17";

    private long acc_exp = 30 * 60 * 1000l;

    private long ref_exp = 3 * 60 * 60 * 1000l;

    public String createAccessToken(MemberResponse member){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + acc_exp));
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT/ACCESS_TOKEN")
                .setClaims(claims)
                .claim("email", member.getEmail())
                .claim("name", member.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createAccessToken(String token){
        Members member = memberService.findMemberByEmail(getEmail(token));
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + acc_exp));
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT/ACCESS_TOKEN")
                .setClaims(claims)
                .claim("email", member.getEmail())
                .claim("name", member.getName())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRefreshToken(){
        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ref_exp));
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT/REFRESH_TOKEN")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String getEmail(String token){
        return (String) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("email");
    }

    public String getName(String token){
        return (String) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("name");
    }

    public Authentication getAuth(String token){
        Members member = memberService.findMemberByEmail(getEmail(token));
        return new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    public String resolveAccessToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }
    public String resolveRefreshToken(HttpServletRequest request){
        String token = request.getHeader("X-RefreshToken");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7);
        }
        return null;
    }

}
