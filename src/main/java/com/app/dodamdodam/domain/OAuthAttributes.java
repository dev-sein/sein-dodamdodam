package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String mobile;
//    private final MemberStatus memberStatus;
    private final MemberType memberType;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//      userNameAttributeName은 .yml에서 설정해 놓은 user-name-attribute 값이다.
        log.info("================={}", userNameAttributeName);

//      registrationId는 네이버 로그인일 경우 naver이고 카카오 로그인일 경우 kakao이다.
        log.info("================={}", registrationId);
        if("naver".equals(registrationId)) {
            return ofNaver(userNameAttributeName, attributes);
        }
        else {
            return ofKakao(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .mobile((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey("id")
//                .memberStatus(MemberStatus.NORMAL)
                .memberType(MemberType.NAVER)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get(userNameAttributeName);

        return OAuthAttributes.builder()
                .email((String) kakaoAccount.get("email"))
                .nameAttributeKey("id")
                .attributes(attributes)
//                .memberStatus(MemberStatus.NORMAL)
                .memberType(MemberType.KAKAO)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberName(name)
                .memberEmail(email)
                .memberPhone(mobile)
                .memberRole(Role.MEMBER)
                .memberStatus(MemberStatus.NORMAL) // 계정 활성화 상태
                .memberType(memberType) //Naver인지 Kakao 인지
                .build();
    }
}
