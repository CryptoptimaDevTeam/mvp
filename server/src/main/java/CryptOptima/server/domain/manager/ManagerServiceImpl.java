package CryptOptima.server.domain.manager;

import CryptOptima.server.common.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final CustomBeanUtils<Manager> beanUtils;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerMapper managerMapper; // Entity -> DTO 변환

    // MNG_ACCOUNT02 :: 관리자 계정 추가
    @Override
    @Transactional
    public void createManager(ManagerDto.Create managerDto) {
        Manager manager = managerMapper.createManagerDtoToManager(managerDto);

        String encryptedPassword = passwordEncoder.encode(manager.getPassword());
        manager.changePassword(encryptedPassword); // password 암호화 필수

        managerRepository.save(manager);
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
