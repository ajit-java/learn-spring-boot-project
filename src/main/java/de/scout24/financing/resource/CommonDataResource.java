package de.scout24.financing.resource;

import de.scout24.financing.exception.ExceptionKey;
import lombok.Data;

@Data
public class CommonDataResource {

    private ExceptionKey key;
    private String message;
}
