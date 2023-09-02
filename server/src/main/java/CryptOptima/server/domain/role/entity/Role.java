package CryptOptima.server.domain.role.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleId;

    @Column(unique = true)
    String roleName;

//    Role에서 GradeRole으로 객체 그래프 탐색할 필요성이 없다.
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    List<GradeRole> gradeRoleList = new ArrayList<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<ResourceRole> resourceRoleList = new ArrayList<>();
}
