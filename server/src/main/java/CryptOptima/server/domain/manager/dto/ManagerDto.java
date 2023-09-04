package CryptOptima.server.domain.manager.dto;

import lombok.*;

public class ManagerDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {

        private String accountId;
        private String password;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String password;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response {
        private Long managerId;
        private String managerName;
        private String managerGrade;
        private String managerInfo;
    }

}