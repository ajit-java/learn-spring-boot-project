package de.scout24.financing.domain;

import static de.scout24.financing.utility.DateTimeHelper.getUtcDatetimeAsDate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractBaseDomain {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field("id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", insertable = true, updatable = false)
    @JsonIgnore
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedAt", insertable = true, updatable = true)
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "IsDeleted")
    @JsonIgnore
    private Boolean deleted;
    
    @PrePersist
    protected void onCreate() {
        createdAt = getUtcDatetimeAsDate();
        updatedAt = getUtcDatetimeAsDate();

        if (deleted == null) {
            deleted = false;
        }

    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = getUtcDatetimeAsDate();
    }

}