package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User extends AbstractBaseDomain {

    @Column(name = "Email")
    private String email;

    @Column(name = "SSO_ID")
    private String ssoId;

    @JsonProperty("ssoId")
    public String getSsoId(){
        return ssoId;
    }
    @JsonProperty("userId")
    public void setSsoId(String ssoId){
        this.ssoId=ssoId;
    }

    @Column(name = "password")
    @JsonIgnore
    private String password;

}