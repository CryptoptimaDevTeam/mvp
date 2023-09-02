package CryptOptima.server.domain.role.dto;

import lombok.*;

public class ResourceRoleDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long resourceId;
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
