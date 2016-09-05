package de.scout24.financing;
//package de.scout24.financing;
//
//import de.scout24.financing.repository.CommentRepository;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.restdocs.config.RestDocumentationConfigurer;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
///**
// * @author Oliver Gierke
// * @soundtrack The Intersphere - Out of phase (Live at Alte Feuerwache Mannheim)
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
//@IntegrationTest("server.port:0")
//public class WebIntegrationTest {
//
//    @Autowired
//    WebApplicationContext context;
//    @Autowired
//    CommentRepository commentRepository;
//
//    MockMvc mvc;
//
//    @Before
//    public void setUp() {
//
//        this.mvc = MockMvcBuilders.webAppContextSetup(context).//
//                apply(new RestDocumentationConfigurer()).//
//                build();
//    }
//
//    @Test
//    @Ignore
//    public void executeConditionalGetRequests() throws Exception {
//
//        // Greeting greeting = greetings.findAll().iterator().next();
//        // URI uri = new
//        // UriTemplate("/greetings/{id}").expand(greeting.getId());
//        //
//        // MockHttpServletResponse response = mvc.perform(get(uri)).//
//        // andExpect(header().string(ETAG, is(notNullValue()))).//
//        // andExpect(header().string(LAST_MODIFIED, is(notNullValue()))).//
//        // andReturn().getResponse();
//        //
//        // // ETag-based
//        //
//        // response = mvc.perform(get(uri).header(IF_NONE_MATCH,
//        // response.getHeader(ETAG))).//
//        // andExpect(status().isNotModified()).//
//        // andExpect(header().string(ETAG, is(notNullValue()))).//
//        // andExpect(header().string(LAST_MODIFIED, is(notNullValue()))).//
//        // andDo(document("if-none-match")).//
//        // andReturn().getResponse();
//        //
//        // // Last-modified-based
//        //
//        // mvc.perform(get(uri).header(IF_MODIFIED_SINCE,
//        // response.getHeader(LAST_MODIFIED))).//
//        // andExpect(status().isNotModified()).//
//        // andExpect(header().string(ETAG, is(notNullValue()))).//
//        // andExpect(header().string(LAST_MODIFIED, is(notNullValue()))).//
//        // andDo(document("if-modified-since"));
//    }
//}