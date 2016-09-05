package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.Answer;
@Data
public class AnswerResource extends Resource<Answer> {
    public AnswerResource(Answer content) {
        super(content);
    }

    private long commentCount;
    private String forumUserName;
    private String userEmail;
}
