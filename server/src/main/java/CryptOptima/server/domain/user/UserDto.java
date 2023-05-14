package CryptOptima.server.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @Builder
    static class Create {
        private String accountId;
        private String status;
    }

    @Getter
    @Builder
    static class Update {
        private String status;
    }

    @Setter
    @Builder
    static class Response {
        private long userId;
        private String accountId;
        private String status;
    }
}
