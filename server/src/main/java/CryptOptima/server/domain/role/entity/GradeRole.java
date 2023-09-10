package CryptOptima.server.domain.role.entity;

import jakarta.persistence.*;

@Entity
public class GradeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long gradeRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    Role role;
}
