package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.scout24.financing.domain.enums.ExfoPermission;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Permission extends AbstractBaseDomain {

    @Enumerated(EnumType.STRING)
    @Column(name = "PermissionName")
    private ExfoPermission permission;

}
