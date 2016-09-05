package de.scout24.financing;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import de.scout24.financing.exception.ExceptionKey;
import de.scout24.financing.resource.AuthenticatedForumUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import de.scout24.financing.exception.ExpertForumException;
import de.scout24.financing.resource.CommonDataResource;
import de.scout24.financing.exception.MissingFieldException;

@ControllerAdvice
public class GlobalControllerHandler {

    @Autowired
    private de.scout24.financing.exception.ExceptionHandler exceptionHandler;

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    void handleMissingFieldException(MissingFieldException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    public CommonDataResource  handleException(Exception ex)  {
        ExpertForumException exception = exceptionHandler.handle(this, ExceptionKey.INTERNAL_ERROR, ex);
        return exception.getData();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public CommonDataResource handleExpertForumException(ExpertForumException exception) {
        return exception.getData();
    }

    //Access logged in user in all views using authenticatedForumUserResource property.
    @ModelAttribute("authenticatedForumUserResource")
    public AuthenticatedForumUserResource getCurrentUser(Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            AuthenticatedForumUserResource authenticatedForumUserResource = (AuthenticatedForumUserResource) authentication.getPrincipal();
            return authenticatedForumUserResource;
        }
        return null;
    }
}
