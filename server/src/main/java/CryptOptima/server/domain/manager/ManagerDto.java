package CryptOptima.server.domain.manager;

import lombok.*;

public class ManagerDto {

    @Getter
    @Builder
    static class Create {
        private String accountId;
        private String password;
        private String managerGrade;
        private String managerName;
    }

    @Getter
    @Builder
    static class Update {
        private String password;
        private String managerGrade;
        private String managerName;
    }

    @Setter
    @Builder
    static class Response {
        private String accountId;
        private String password;
        private String managerGrade;
        private String managerName;
    }

}
