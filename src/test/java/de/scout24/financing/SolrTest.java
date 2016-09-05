package de.scout24.financing;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.scout24.financing.domain.Question;
import de.scout24.financing.repository.QuestionRepository;
import de.scout24.financing.service.impl.QuestionService;
import de.scout24.financing.service.impl.SearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@WithMockUser(roles = "USER")
public class SolrTest {

    @Autowired
    @InjectMocks
    private QuestionService questionService;

    @Autowired
    @InjectMocks
    private SearchService searchService;

    @Autowired
    @Mock
    private QuestionRepository questionRepository;
    
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(new Question());
        Mockito.doNothing().when(questionRepository).delete(Mockito.anyLong());
        createSampleData();
    }
    
    @After
    public void after() {
        removeSampleData();
    }

    private void createSampleData() {
        Question question = new Question();
        question.setId(1L);
        question.setTitle("A question");
        question.setText("Bla bla bla.");
        questionService.addQuestion(question);
    }
    
    private void removeSampleData() {
        questionService.removeQuestionById(1L);
    }

    @Test
    @Ignore("fix me!")
    public void findQuestionByContainingTerm() {
        List<Question> questions = searchService.search("bla", new PageRequest(0, 10)).getContent();
        assertEquals(1, questions.size());
    }

    @Test
    @Ignore("fix me!")
    public void findQuestionByNotContainingTerm() {
        List<Question> questions = searchService.search("answer", new PageRequest(0, 10)).getContent();
        assertEquals(0, questions.size());
    }
}
