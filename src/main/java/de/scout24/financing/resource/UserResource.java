package de.scout24.financing.resource;

import lombok.Data;
import org.springframework.hateoas.Resource;

import de.scout24.financing.domain.User;
@Data
public class UserResource extends Resource<User> {
    public UserResource(User content) {
        super(content);
    }
}
