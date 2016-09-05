package de.scout24.financing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ExpertRating extends AbstractBaseDomain {

    // user who rated the expert
    @Column(name = "ID_User_Rated")
    private long userRatedId;

    @Column(name = "RatingText")
    @Type(type = "text")
    private String ratingText;

    @Column(name = "StarRatingValue")
    private int startRatingValue;

    @ManyToOne
    @JoinColumn(name = "ID_RatingUser")
    public ForumUser ratingUser;
}