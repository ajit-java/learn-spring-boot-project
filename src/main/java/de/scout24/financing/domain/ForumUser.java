package de.scout24.financing.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ForumUser extends AbstractBaseDomain {

    @Column(name = "NickName")
    private String nickName;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Role")
    private Role role;
    
    @Column(name = "Gender")
    private String gender;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Street")
    private String street;

    @Column(name = "Email")
    private String email;

    @Column(name = "ZipCode")
    private String zipCode;

    @Column(name = "City")
    private String city;

    @Column(name = "Telephone")
    private String telephone;

    @Column(name = "Mobile")
    private String mobile;

    @Transient
    @JsonProperty("password")
    private String rawPassword;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_User")
    private User user;

    @Column(name = "ProfileText")
    @Type(type = "text")
    private String profileText;

    @Column(name = "Expertise")
    private String expertise;
}