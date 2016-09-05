package de.scout24.financing.domain;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.solr.client.solrj.beans.Field;

import de.scout24.financing.domain.enums.PublishingStatus;
import de.scout24.financing.domain.enums.QuestionStatus;
import lombok.Data;

@Data
@Entity
public class Question extends AbstractPublishable {

    @Column(name = "Title")
    @Field("title_s")
    private String title;

    @Column(name = "text")
    @Field("text_s")
    private String text;

    @Column(name = "ID_Category")
    private long categoryId;

    @Column(name = "QuestionStatus")
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @PrePersist
    protected void onCreate() {
        questionStatus = QuestionStatus.OPEN;
        super.onCreate();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id")
    @JoinColumn(name = "ID_ForumUser")
    @JsonIgnore
    private ForumUser forumUser;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_favorite_questions", joinColumns = @JoinColumn(name = "question_id") , inverseJoinColumns = @JoinColumn(name = "forumuser_id"))
    private Set<ForumUser> usersAsFavorite;

    // @OneToMany(fetch = FetchType.LAZY)
    // @MapsId("ID")
    // @JoinColumn(name = "ID_Question")
    // private List<Answer> answers;
    
    public Question() {
        super.setPublishingStatus(PublishingStatus.NEW);
    }


}
