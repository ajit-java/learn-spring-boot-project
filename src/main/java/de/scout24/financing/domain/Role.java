package de.scout24.financing.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import de.scout24.financing.domain.enums.ExfoRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role extends AbstractBaseDomain {

    @Column(name = "RoleName")
    @Enumerated(EnumType.STRING)
    private ExfoRole name;
    
    @ManyToMany
    @JoinTable(name = "Role_Permission", joinColumns = @JoinColumn(name = "ID_Role"), inverseJoinColumns = @JoinColumn(name = "ID_Permission"))
    private Set<Permission> permissions;
    
    public Role(ExfoRole role) {
        this.name = role;
    }

}
