package CryptOptima.server.domain.manager;

public interface ManagerService {
    // MNG_ACCOUNT02 :: 관리자 계정 추가
    void createManager(ManagerDto.Create managerDto);

    // MNG_ACCOUNT03 :: 관리자 계정 수정
    void updateManager(ManagerDto.Update managerDto, Long managerId);

    // MNG_ACCOUNT04 :: 관리자 계정 삭제
    void deleteManager(Long managerId);

    // TODO Application Layer에서 DTO -> Entity 변환 작업이 옳은지, Presentation Layer에서 DTO -> Entity 변환 작업이 옳은지
}
