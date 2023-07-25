package CryptOptima.server.security.utils;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class OAuth2AttributeUtils {
    public static Map<String, Object> getAttributes(OAuth2AuthenticationToken token) {
        switch (token.getAuthorizedClientRegistrationId()) {
            case "google":
                return ofGoogle(token.getPrincipal());
            case "twitter":
                return ofTwitter(token.getPrincipal());
            default:
                return ofFacebook(token.getPrincipal());
        }
    }

    private static Map<String, Object> ofGoogle(OAuth2User oAuth2User) {
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("registrationId", "google");
        attribute.put("name",oAuth2User.getAttribute("name"));
        attribute.put("email",oAuth2User.getAttribute("email"));
        attribute.put("locale",oAuth2User.getAttribute("locale"));
        attribute.put("profileImg",oAuth2User.getAttribute("picture"));
        return attribute;
    }

    private static Map<String, Object> ofTwitter(OAuth2User oAuth2User) {
        return null;
    }

    private static Map<String, Object> ofFacebook(OAuth2User oAuth2User) {
        return null;
    }
}