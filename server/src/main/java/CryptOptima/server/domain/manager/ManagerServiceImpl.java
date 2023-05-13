package CryptOptima.server.domain.manager;

import CryptOptima.server.common.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final CustomBeanUtils<Manager> beanUtils;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerMapper managerMapper; // Entity -> DTO 변환

    private final List<String> KING_ROLES_STRING = List.of("KING","MASTER","MANAGER");
    private final List<String> MASTER_ROLES_STRING = List.of("MASTER","MANAGER");
    private final List<String> MANAGER_ROLES_STRING = List.of("MANAGER");

    // MNG_ACCOUNT02 :: 관리자 계정 추가
    @Override
    @Transactional
    public void createManager(ManagerDto.Create managerDto) {
        Manager manager = managerMapper.createManagerDtoToManager(managerDto);

        String encryptedPassword = passwordEncoder.encode(manager.getPassword());
        manager.changePassword(encryptedPassword); // password 암호화 필수

        manager.changeRoles(getRoles(managerDto.getManagerGrade()));

        managerRepository.save(manager);
    }

    private List<String> getRoles(String managerGrade) {
        switch (managerGrade) {
            case "KING" :
                return KING_ROLES_STRING;
            case "MASTER":
                return MASTER_ROLES_STRING;
            default:
                return MANAGER_ROLES_STRING;
        }
    }

    // MNG_ACCOUNT03 :: 관리자 계정 수정
    @Override
    @Transactional
    public void updateManager(ManagerDto.Update managerDto, Long managerId) {
        Manager manager = managerMapper.updateManagerDtoToManager(managerDto);
        String encryptedPassword = passwordEncoder.encode(manager.getPassword());
        manager.changePassword(encryptedPassword); // password 암호화 필수

        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        Manager findManager = optionalManager.orElseThrow(()->new RuntimeException("ManagerNotFound"));

        managerRepository.save(beanUtils.copyNonNullProperties(manager, findManager));
    }

    // MNG_ACCOUNT04 :: 관리자 계정 삭제
    @Override
    public void deleteManager(Long managerId) {
        managerRepository.deleteById(managerId);
    }
}
