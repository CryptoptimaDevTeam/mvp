package CryptOptima.server.auth.oauth.dto;

import CryptOptima.server.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * SNS로 부터 사용자 정보를 담아오는 객체
 */
@Getter
@ToString
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String email;
//  private String ageRange;
//  private String gender;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributesKey, String email) {
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.email = email;
    }

    public static OAuthAttributes of(String sns, Map<String, Object> attributes) {
        if("google".equals(sns)) return ofGoogle("sub", attributes);
        return null;
    }

    private static OAuthAttributes ofGoogle(String userNameAttributesKey, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributesKey(userNameAttributesKey)
                .attributes(attributes)
                .email((String) attributes.get("email"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .accountId(email)
                .status("ACTIVE")
                .paybackCumAmount("0")
                .paybackFinishedAmount("0")
                .paybackTotalRequestedAmount("0")
                .build();
    }

    // TODO ofFacebook, ofTwitter
}
