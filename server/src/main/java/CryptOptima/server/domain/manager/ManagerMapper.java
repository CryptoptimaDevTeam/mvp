package CryptOptima.server.domain.manager;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManagerMapper {

    // MNG_ACCOUNT02 :: 관리자 계정 추가
    public Manager createManagerDtoToManager(ManagerDto.Create managerDto) {
        return Manager.builder()
                .accountId(managerDto.getAccountId())
                .password(managerDto.getPassword())
                .managerGrade(managerDto.getManagerName())
                .managerName(managerDto.getManagerName())
                .build();
    }

    // MNG_ACCOUNT03 :: 관리자 계정 수정
    public Manager updateManagerDtoToManager(ManagerDto.Update managerDto) {
        return Manager.builder()
                .password(managerDto.getPassword())
                .managerGrade(managerDto.getManagerGrade())
                .managerName(managerDto.getManagerGrade())
                .build();
    }
}
