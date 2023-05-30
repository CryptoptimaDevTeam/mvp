package CryptOptima.server.auth.oauth.dto;

import CryptOptima.server.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * SNS 별 공통 attributes를 뽑아낸다. email, username, attributes
 */
@Getter
@ToString
public class OAuthAttributes {
    private String email;
    private String username;
    private Map<String, Object> attributes;

    @Builder // 공통 생성자
    public OAuthAttributes(Map<String, Object> attributes, String username, String email) {
        this.email = email;
        this.username = username;
        this.attributes = attributes;
    }

    // SNS 별 생성자
    public static OAuthAttributes of(String sns, Map<String, Object> attributes) {
        if(sns.equals("google")) return ofGoogle(attributes);
//        else if(sns.equals("twitter")) return ofTwitter(attributes);
//        else return ofFacebook(attributes);
        else return null;
    }

    private static OAuthAttributes ofGoogle(Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .username((String) attributes.get("name"))
                .attributes(attributes)
                .build();
    }

    public User toEntity(String registrationId) {
        return User.builder()
                .accountId(email)
                .registrationId(registrationId)
                .username(username)
                .status("ACTIVE")
                .paybackCumAmount("0")
                .paybackFinishedAmount("0")
                .paybackTotalRequestedAmount("0")
                .build();
    }

    // TODO ofFacebook, ofTwitter
}
