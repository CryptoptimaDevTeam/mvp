package CryptOptima.server.auth.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private String accountId;
    private String password;
}
