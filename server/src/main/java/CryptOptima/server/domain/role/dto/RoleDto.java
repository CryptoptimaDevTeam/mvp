package CryptOptima.server.domain.role.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RoleDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank
        @Pattern(regexp = "^ROLE_",message = "Please enter a valid format of permissions.")
        private String roleName;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @Pattern(regexp = "^ROLE_",message = "Please enter a valid format of permissions.")
        private String roleName;
    }


    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long roleId;
        private String roleName;
    }
}
