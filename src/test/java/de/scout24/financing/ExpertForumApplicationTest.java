package de.scout24.financing;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.scout24.financing.exception.ExceptionHandler;
import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.exception.ExpertForumException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExpertForumApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ExpertForumApplicationTest {

    @Autowired
    private ExceptionHandler exceptionHandler;
    
    @Test
    @Ignore("empty")
    public void contextLoads() {
    }
    
    @Test(expected = ExpertForumException.class)
    public void testExceptions() throws ExpertForumException {
        ExpertForumException exception = exceptionHandler.handle(this, ExceptionKey.INTERNAL_ERROR, new Exception("test exception"));
        assertEquals("INTERNAL_ERROR", exception.getData().getKey().name());
        assertEquals("Fehler!", exception.getData().getMessage());
        throw exception;
    }

}
