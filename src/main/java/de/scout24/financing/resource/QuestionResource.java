package de.scout24.financing.resource;

import de.scout24.financing.domain.enums.NotificationStatus;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.Question;
import java.util.Date;

@Data
public class QuestionResource extends Resource<Question> {
    public QuestionResource(Question content) {
        super(content);
    }

    private long answerCount;
    private long commentCount;
    private String forumUserName;
    private String userEmail;
    private Date lastActivity;
    private NotificationStatus notificationStatus;
    private boolean isfavorite;
    private long favoriteId;
}
