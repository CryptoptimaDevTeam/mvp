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
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resourceId;

    @Column(unique = true)
    String resourceUrl;

    @Column(unique = true, name = "resource_order")
    int order;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    List<ResourceRole> resourceRoleList = new ArrayList<>();

    public void updateResourceRoleList(List<ResourceRole> resourceRoleList) {
        this.resourceRoleList = resourceRoleList;
    }
}
