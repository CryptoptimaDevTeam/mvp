package CryptOptima.server.domain.role.dto;

import lombok.*;

import java.util.List;

public class ResourceDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String url;
        private int order;
        private List<Long> roleIds;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long resourceId;
        private String resourceUrl;
        private int order;
        private List<ResourceRoleDto.Response> resourceRoleList;
    }
}
