package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ExpertProfile extends AbstractBaseDomain {

    @Column(name = "RegistrationNumber")
    private String registrationNumber;

    @Column(name = "HrbNumber")
    private String hrbNumber;

    @Column(name = "DistrictCourt")
    private String districtCourt;

    @Column(name = "ProfileText")
    @Type(type = "text")
    private String profileText;

    @Column(name = "Expertise")
    private String expertise;

    @Column(name = "ID_ForumUser")
    private long forumUserId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "ID_ForumUser")
    private ForumUser forumUser;

}