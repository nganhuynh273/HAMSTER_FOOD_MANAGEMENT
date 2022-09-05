package com.codegym.hpn.service;

import com.codegym.hpn.model.Role;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
