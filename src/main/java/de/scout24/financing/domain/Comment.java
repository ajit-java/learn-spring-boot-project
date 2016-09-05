package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Type;

import de.scout24.financing.domain.enums.PublishingStatus;
import lombok.Data;

@Data
@Entity
public class Comment extends AbstractPublishable {

    @Column(name = "CommentText")
    @Type(type = "text")
    private String commentText;
    @Column(name = "ID_Question")
    private long questionId;
    @Column(name = "ID_Answer")
    private long answerId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "ID_ForumUser")
    private ForumUser forumUser;

    public Comment() {
        super.setPublishingStatus(PublishingStatus.APPROVED);
    }

}
