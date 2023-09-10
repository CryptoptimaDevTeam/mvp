package CryptOptima.server.domain.role.entity;

import CryptOptima.server.domain.role.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleId;

    @Column(unique = true)
    String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<GradeRole> gradeRoleList = new ArrayList<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    List<ResourceRole> resourceRoleList = new ArrayList<>();

    // todo null 여부 판단
    public void updateRoleName(RoleDto.Update updateRoleDto) {
        this.roleName = updateRoleDto.getRoleName();
    }
}
