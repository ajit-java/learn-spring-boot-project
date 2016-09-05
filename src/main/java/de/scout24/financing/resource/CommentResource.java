package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.Comment;
@Data
public class CommentResource extends Resource<Comment> {
    public CommentResource(Comment content) {
        super(content);
    }

    private boolean expertUserComment;
    private String forumUserName;
    private String userEmail;
}
