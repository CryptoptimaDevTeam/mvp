package CryptOptima.server.domain.role.dto;

import lombok.*;

public class RoleDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String roleName;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
//        private Long roleId;
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
