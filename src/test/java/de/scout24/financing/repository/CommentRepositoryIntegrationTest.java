package de.scout24.financing.repository;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.scout24.financing.ExpertForumApplication;
import de.scout24.financing.domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
@WebAppConfiguration
public class CommentRepositoryIntegrationTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Before
    public void setUp() throws Exception {
       // commentRepository.deleteAll();
    }

    @WithMockUser(username = "admin", password = "is24")
    @Test
    @Ignore
    public void canSaveComment() {
        final Comment comment = new Comment();
        comment.setCommentText("neues comment");
        comment.setAnswerId(answerRepository.findAll().iterator().next().getId());
        comment.setForumUserId(1);
        comment.setQuestionId(1);
        Comment dbComment = commentRepository.save(comment);
        Assert.assertThat(commentRepository.findOne(dbComment.getId()), CoreMatchers.is(comment));
    }
}
