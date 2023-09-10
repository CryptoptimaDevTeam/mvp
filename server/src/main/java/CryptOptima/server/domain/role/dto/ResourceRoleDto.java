package CryptOptima.server.domain.role.dto;

import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ResourceRoleDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @Min(1)
        @NotBlank
        private Long resourceId;
        @Min(1)
        @NotBlank
        private Long roleId;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long resourceRoleId; // 삭제 용이성
        private String roleName;
    }
}
