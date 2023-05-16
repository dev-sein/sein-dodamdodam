//package com.app.dodamdodam.provider;
//
//import com.app.dodamdodam.entity.member.Member;
//import com.app.dodamdodam.type.MemberStatus;
//import com.app.dodamdodam.type.MemberType;
//import com.app.dodamdodam.type.Role;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Map;
//
//@Getter
//@Builder
//@RequiredArgsConstructor
//public class OAuthAttributes {
//    private final Map<String, Object> attributes;
//    private final String nameAttributeKey;
//    private final String name;
//    private final String email;
//    private final String mobile;
//
//    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
//        if("naver".equals(registrationId)) {
//            return ofNaver(userNameAttributeName, attributes);
//        }
//        else {
////            return ofKakao("id", attributes);
//            return null;
//        }
//    }
//
//
//    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        return OAuthAttributes.builder()
//                .name((String) response.get("name"))
//                .email((String) response.get("email"))
//                .mobile((String) response.get("mobile"))
//                .attributes(response)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public Member toEntity() {
//        return Member.builder()
//                .memberName(name)
//                .memberEmail(email)
//                .memberRole(Role.MEMBER)
//                .memberPhone(mobile)
//                .memberStatus(MemberStatus.NORMAL)
//                .memberType(MemberType.NAVER)
//                .build();
//    }
//}