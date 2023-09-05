package CryptOptima.server.domain.manager.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ManagerDto {

    // Todo managerGrade 정규표현식 하드코딩 개선

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @Email
        @NotBlank
        private String accountId;
        @NotBlank
        private String password;
        @NotBlank
        private String managerName;
        @NotBlank
        @Pattern(regexp = "^ROLE_(MANAGER|ADMIN)",message = "Please enter a valid format of permissions.")
        private String managerGrade;
        @NotBlank
        private String managerInfo;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String password;
        private String managerName;
        @Pattern(regexp = "^ROLE_(MANAGER|ADMIN)",message = "Please enter a valid format of permissions.")
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