package de.scout24.financing.service.impl;

import de.scout24.financing.domain.enums.MessageKey;
import de.scout24.financing.exception.ExceptionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by ajit on 20.11.15.
 */
@Service
public abstract class BaseService {
  @Autowired
  protected ServiceHelper serviceHelper;

  @Autowired
  private MessageSource messageSource;
  private static final String ERROR_PROPERTY_PREFIX = "error";
  private static final String MESSAGE_PROPERTY_PREFIX = "message";

  protected String getErrorMessageFromResource(ExceptionKey key) {
    return messageSource.getMessage(ERROR_PROPERTY_PREFIX + "." + key, null, LocaleContextHolder.getLocale());
  }
  protected String getMessageFromResource(MessageKey key) {
    return messageSource.getMessage(MESSAGE_PROPERTY_PREFIX + "." + key, null, LocaleContextHolder.getLocale());
  }
}
