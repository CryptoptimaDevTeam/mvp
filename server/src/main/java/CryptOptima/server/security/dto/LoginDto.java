package CryptOptima.server.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Email
    @NotBlank
    private String accountId;
    @NotBlank
    private String password;
}
