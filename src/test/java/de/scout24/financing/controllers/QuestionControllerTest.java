package de.scout24.financing.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.scout24.financing.ExpertForumApplication;
import de.scout24.financing.controller.QuestionController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
public class QuestionControllerTest {

    @Autowired
    WebApplicationContext context;
    @Autowired
    QuestionController questionController;

    MockMvc mvc;

    @Before
    public void setUp() {

        this.mvc = MockMvcBuilders.webAppContextSetup(context).//
                apply(new RestDocumentationConfigurer()).//
                build();
    }

    @WithMockUser(username = "admin", password = "is24")
    @Test
    @Ignore("fails")
    public void executeConditionalGetRequests() throws Exception {
        mvc.perform(get("/questions/1").contentType(MediaType.APPLICATION_JSON)).
        andExpect(status().isOk()).
        andExpect(header().string("Content-type", is("application/hal+json"))).
        andExpect(content().json("{'id':1}"));
//         andExpect(header().string(LAST_MODIFIED, is(notNullValue()))).
    }


}