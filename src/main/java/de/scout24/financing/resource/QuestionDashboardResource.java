package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

@Data
public class QuestionDashboardResource extends ResourceSupport {
    private long newQuestionCount;
    private long approvedQuestionCount;
    private long spamQuestionCount;
    private long rejectedQuestionCount;
}
