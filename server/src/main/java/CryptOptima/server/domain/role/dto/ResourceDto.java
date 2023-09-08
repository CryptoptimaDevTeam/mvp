package CryptOptima.server.domain.role.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
