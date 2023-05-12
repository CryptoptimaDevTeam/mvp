package CryptOptima.server.domain.manager;

import lombok.*;

public class ManagerDto {

    @Getter
    @Builder
    static class Create {
        private String accountId;
        private String password;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

    @Getter
    @Builder
    static class Update {
        private String password;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

    @Setter
    @Builder
    static class Response {
        private Long managerId;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

}
