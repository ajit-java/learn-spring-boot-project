package de.scout24.financing;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;

import de.scout24.financing.domain.Answer;
import de.scout24.financing.domain.Category;
import de.scout24.financing.domain.Comment;
import de.scout24.financing.domain.ForumUser;
import de.scout24.financing.domain.NotificationRule;
import de.scout24.financing.domain.Question;
import de.scout24.financing.domain.User;

/**
 * Created by ajitchahal on 31/08/15.
 */

@Configuration
public class RestMvcConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Question.class, Answer.class, Category.class, Comment.class, ForumUser.class, User.class, NotificationRule.class);
    }

// This is commented as it makes all object loading as eager by default and format of JSON is changed to Hateos format with all the links embedded.
// This makes all the UI implementation incompatible.
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
//    }
}
