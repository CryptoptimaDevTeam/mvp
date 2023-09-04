package CryptOptima.server.domain.manager.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ManagerDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank(message = "AccountId is mandatory.")
        private String accountId;
        @NotBlank(message = "Password is mandatory.")
        private String password;
        @NotBlank(message = "ManagerName is mandatory.")
        private String managerName;
        @NotBlank(message = "ManagerGrade is mandatory.")
        @Pattern(regexp = "^ROLE_[A-Z]+?$")
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