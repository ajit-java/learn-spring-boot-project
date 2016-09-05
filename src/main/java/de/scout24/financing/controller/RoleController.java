package de.scout24.financing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.scout24.financing.domain.Role;
import de.scout24.financing.repository.RoleRepository;

@RestController
public class RoleController {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @RequestMapping(value = "/roles/all", method = RequestMethod.GET)
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
