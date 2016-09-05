package de.scout24.financing;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SecurityTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(new RestDocumentationConfigurer()).apply(springSecurity()).build();
    }

    @Test
    public void testUnSecuredResourceRequests() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get("/view").param("viewName", "/views/questions/add")).andExpect(status().isOk());
    }
    
    @Test
    public void testSecuredRecourceRequests() throws Exception {
        mvc.perform(get("/questions/all")).andExpect(status().isForbidden());
    }
    
    @Test
    @Ignore("fails")
    @WithMockUser(roles = "USER_REQUESTED")
    public void testUserSecuredResourceRequests() throws Exception {
        mvc.perform(get("/questions/all")).andExpect(status().isOk());
    }

    @Test
    @Ignore("empty")
    @WithMockUser(roles = "ADMIN")
    public void testAdminSecuredResourceRequests() throws Exception {
        //TODO
    }

}
