package CryptOptima.server.domain.role.dto;

import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ResourceDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @NotBlank
        private String url;
        @Min(1)
        @NotNull
        private int order;
        @NotEmpty
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
