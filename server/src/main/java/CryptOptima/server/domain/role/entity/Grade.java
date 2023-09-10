package CryptOptima.server.domain.role.entity;

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
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long gradeId;

    @Column(unique = true)
    String gradeName;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    List<GradeRole> gradeRoleList = new ArrayList<>();

    public void updateGradeRoleList(List<GradeRole> gradeRoleList) {
        this.gradeRoleList = gradeRoleList;
    }
}
