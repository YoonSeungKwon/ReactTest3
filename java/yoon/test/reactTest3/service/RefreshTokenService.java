package yoon.test.reactTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.test.reactTest3.domain.RefreshToken;
import yoon.test.reactTest3.repository.MemberRepository;
import yoon.test.reactTest3.repository.RefreshTokenRepository;
import yoon.test.reactTest3.security.jwt.JwtProvider;
import yoon.test.reactTest3.vo.response.MemberResponse;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository tokenRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public String updateRefreshToken(MemberResponse memberResponse){

        String email = memberResponse.getEmail();

        if(tokenRepository.existsRefreshTokenByMember_Email(email)){
            RefreshToken saved_token = tokenRepository.findRefreshTokenByMember_Email(email);
            saved_token.setToken(jwtProvider.createRefreshToken());
            tokenRepository.save(saved_token);
            return saved_token.getToken();
        }

        RefreshToken token = RefreshToken.builder()
                .member(memberRepository.findMembersByEmail(email))
                .token(jwtProvider.createRefreshToken())
                .build();

        tokenRepository.save(token);

        return token.getToken();
    }
}
