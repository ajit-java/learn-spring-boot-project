package de.scout24.financing.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import de.scout24.financing.domain.enums.PublishingStatus;
import lombok.Data;

@Data
@MappedSuperclass
public class AbstractPublishable extends AbstractBaseDomain {

    @Column(name = "ID_ForumUser")
    private long forumUserId;
    @Column(name = "PublishedAt")
    private Date publishedAt;
    @Column(name = "PublishingStatus")
    @Enumerated(EnumType.STRING) 
    private PublishingStatus publishingStatus;
}
