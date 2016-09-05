package de.scout24.financing.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import de.scout24.financing.resource.CommonDataResource;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ExpertForumException extends Exception {
    
    private static final String ERROR_PROPERTY_PREFIX = "error";
    
    private CommonDataResource data;
    
    public ExpertForumException(MessageSource messageSource, ExceptionKey key) {
        data = new CommonDataResource();
        data.setKey(key);
        data.setMessage(messageSource.getMessage(ERROR_PROPERTY_PREFIX + "." + key, null, LocaleContextHolder.getLocale()));
    }
    
}
