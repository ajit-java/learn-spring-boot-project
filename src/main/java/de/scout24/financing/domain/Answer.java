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
public class Answer extends AbstractPublishable {

    @Column(name = "AnswerText")
    @Type(type = "text")
    private String answerText;
    @Column(name = "ID_Question")
    private long questionId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "ID_ForumUser")
    private ForumUser forumUser;

    public Answer() {
        super.setPublishingStatus(PublishingStatus.APPROVED);
    }
}