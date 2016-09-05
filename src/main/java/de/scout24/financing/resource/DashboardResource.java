package de.scout24.financing.resource;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

@Data
public class DashboardResource extends ResourceSupport {
    private long newQuestionCount;
    private long newExpertsCount;
    private long approvedQuestionCount;
    private long answersCount;
    private long commentsCount;
    private long userCount;
    private long expertsCount;
    private long closedQuestionCount;
    private long spamQuestionCount;
    private long categoryCount;

}
