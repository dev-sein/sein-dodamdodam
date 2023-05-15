package com.app.dodamdodam.service.member;

import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.provider.OAuthAttributes;
import com.app.dodamdodam.provider.UserDetail;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    private final HttpSession httpSession;

    /* 로그인 된 유저 정보 */
    @Override
    public Optional<Member> getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberRepository.save(toMemberEntity(memberDTO));

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByMemberId_QueryDSL(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return UserDetail.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberPhone(member.getMemberPhone())
                .memberName(member.getMemberName())
                .memberRole(member.getMemberRole())
                .memberStatus(member.getMemberStatus())
                .memberType(member.getMemberType())
                .build();
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        log.info(userRequest.getClientRegistration().getProviderDetails().toString());
        // naver, kakao 로그인 구분
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes);

        httpSession.setAttribute("name", member.getMemberName()); // name
        httpSession.setAttribute("email", member.getMemberEmail()); // email
        httpSession.setAttribute("mobile", member.getMemberPhone()); // phone

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findMemberByMemberEmail_QueryDSL(attributes.getEmail())
                .map(entity -> entity.update(attributes.getEmail(), attributes.getMobile(), attributes.getName()))
                .orElse(attributes.toEntity());

        return memberRepository.save(member);
    }
}
