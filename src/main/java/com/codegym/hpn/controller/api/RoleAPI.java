package com.codegym.hpn.controller.api;

import com.codegym.hpn.model.Role;
import com.codegym.hpn.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleAPI {
    @Autowired
    IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles () {
        Iterable<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}

