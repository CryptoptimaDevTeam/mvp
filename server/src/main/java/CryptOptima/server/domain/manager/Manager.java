package CryptOptima.server.domain.manager;

import CryptOptima.server.domain.BaseTimeEntity;

import javax.persistence.*;

/**
 * Manager
 * 관리자 엔티티. managerName, password으로 로그인 한다.
 */

@Entity
public class Manager extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long managerId;

    @Column(unique = true, nullable = false)
    private String managerAccountId; // 아이디

    @Column(nullable = false)
    private String managerPassword;

    @Column(nullable = false)
    private String managerGrade; // Role

    @Column(nullable = false)
    private String managerName; // 닉네임

    // 관리자 계정 패스워드를 변경한다.
    public void changeManagerPassword(String newPassword) {
        this.managerPassword = newPassword;
    }

    // 관리자 계정 이름을 변경한다.
    public void changeManagerName(String newName) {
        this.managerName = newName;
    }

    // 관리자 계정 권한을 변경한다.
    // TODO King만 다른 매니저들의 권한을 변경할 수 있다.
    public void changeManagerGrade(String newGrade) {
        this.managerGrade = newGrade;
    }
}
