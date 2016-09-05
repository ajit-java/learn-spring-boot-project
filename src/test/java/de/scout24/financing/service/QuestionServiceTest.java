package de.scout24.financing.service;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import de.scout24.financing.domain.Question;
import de.scout24.financing.repository.QuestionRepository;
import de.scout24.financing.service.impl.QuestionService;

/**
 * Initially created by Tino on 05.08.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;

    @Before
    public void setUp() throws Exception {
        //when ever findOne method is used return a mock question object
        Mockito.when(questionRepository.findOne(1L)).thenReturn(new Question());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Ignore
    public void testGetGreeting() throws Exception {
        assertThat(questionService.getQuestion(1L), is(not(nullValue())));

    }
}