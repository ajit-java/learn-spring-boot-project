package de.scout24.financing.exception;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    
    public ExpertForumException handle(Object source, ExceptionKey key, Exception e) {
        return handle(source, key, e, "");
    }

    public ExpertForumException handle(Object source, ExceptionKey key, Exception e, String customMessage) {
        LoggerFactory.getLogger(source.getClass()).error(customMessage + " :: " + e.getMessage(), e);
        return new ExpertForumException(messageSource, key);
    }
}
