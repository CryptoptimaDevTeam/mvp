package CryptOptima.server.domain.manager;

import CryptOptima.server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager
 * 관리자 엔티티. managerName, password으로 로그인 한다.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter // SpringSecurity ManagerDetails 생성에 필요
@Entity
public class Manager extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    @Column(unique = true, nullable = false)
    private String accountId; // 아이디

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String managerGrade; // Role

    @Column(nullable = false)
    private String managerName; // 닉네임

    @Column
    private String managerInfo; // 계정 정보

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>(); // 매니저 권한 정보


    // 관리자 계정 패스워드를 변경한다.
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // 관리자 계정 이름을 변경한다.
    public void changeManagerName(String newName) {
        this.managerName = newName;
    }

    // 관리자 계정 권한을 변경한다. grade에 따른 테이블 row를 삭제/추가한다.
    public void changeManagerGrade(String newGrade) {
        this.managerGrade = newGrade;
    }

    public void changeRoles(List<String> roles) {
        this.roles = roles;
    }
}
