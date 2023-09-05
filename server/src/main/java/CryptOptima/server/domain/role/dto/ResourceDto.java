package CryptOptima.server.domain.role.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class ResourceDto {

    // Todo List 유효성 검증

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank
        private String url;
        @Min(1)
        @NotBlank
        private int order;
        @NotBlank
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
