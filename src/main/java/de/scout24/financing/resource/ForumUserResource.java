package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.ForumUser;
@Data
public class ForumUserResource extends Resource<ForumUser> {
    public ForumUserResource(ForumUser content) {
        super(content);
    }

    private long questionCount;
    private long commentCount;
}
