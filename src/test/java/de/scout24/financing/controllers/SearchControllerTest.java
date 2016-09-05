package de.scout24.financing.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.scout24.financing.ExpertForumApplication;
import de.scout24.financing.controller.SearchController;
import de.scout24.financing.domain.Question;
import de.scout24.financing.service.impl.SearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
public class SearchControllerTest {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    @InjectMocks
    private SearchController searchController;
    
    @Mock
    private SearchService searchService;

    MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(new RestDocumentationConfigurer()).build();
        Mockito.when(searchService.search(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(new PageImpl<Question>(new ArrayList<Question>()));
    }

    @Test
    public void testSearch() throws Exception {
        mvc.perform(get("/search/foo").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string("Content-type", is("application/hal+json")));
        Mockito.verify(searchService).search(Mockito.anyString(), Mockito.any(Pageable.class));
    }

}
