package com.codegym.hpn.model;

import com.codegym.hpn.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private String address;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserDTO toUserDTO() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");

        return new UserDTO()
                .setId(this.id)
                .setFullName(this.fullName)
                .setPhoneNumber(this.phoneNumber)
                .setAddress(this.address)
                .setUsername(this.username)
                .setRole(this.role.toRoleDTO())
                .setCreationDate(dateFormat.format(this.getCreatedAt()));
    }
}
