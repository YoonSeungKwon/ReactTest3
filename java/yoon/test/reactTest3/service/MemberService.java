package yoon.test.reactTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoon.test.reactTest3.domain.Members;
import yoon.test.reactTest3.enums.Role;
import yoon.test.reactTest3.repository.MemberRepository;
import yoon.test.reactTest3.vo.request.MemberDto;
import yoon.test.reactTest3.vo.request.MemberRequest;
import yoon.test.reactTest3.vo.response.MemberResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private MemberResponse toResponse(Members member){
        return new MemberResponse(member.getEmail(), member.getName(), member.getRoleValue(), member.getRegdate());
    }

    @Transactional(readOnly = true)
    public boolean checkEmailDuplication(String email){
        return memberRepository.existsByEmail(email);
    }

    @Transactional
    public MemberResponse join(MemberDto dto) {

        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(Role.USER)
                .build();

        return toResponse(memberRepository.save(member));
    }

    public MemberResponse login(MemberRequest dto){

        String username = dto.getEmail();
        String password = dto.getPassword();

        Members member = memberRepository.findMembersByEmail(username);

        if(member == null) {
            System.out.println("UsernameNotFound : " + username);
            throw new UsernameNotFoundException(username);
        }
        if(!passwordEncoder.matches(password, member.getPassword())) {
            System.out.println("BadCredential : " + username);
            throw new BadCredentialsException(username);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return toResponse(member);
    }

    public Members findMemberByEmail(String email)throws UsernameNotFoundException{
        Members member = memberRepository.findMembersByEmail(email);
        if(member == null)
            throw new UsernameNotFoundException(email);
        return member;
    }

    public MemberResponse getResponse(Members member){
        return toResponse(member);
    }
}
