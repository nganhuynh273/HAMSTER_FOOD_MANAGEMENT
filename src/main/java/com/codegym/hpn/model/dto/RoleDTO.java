package com.codegym.hpn.model.dto;

import com.codegym.hpn.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RoleDTO {

    @NotNull(message = "ID Role không được để trống.")
    private Long id;

    private String code;

    public Role toRole() {
        return new Role()
                .setId(id)
                .setCode(code);
    }

}

