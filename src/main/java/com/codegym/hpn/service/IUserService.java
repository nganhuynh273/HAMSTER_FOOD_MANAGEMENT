package com.codegym.hpn.service;

import com.codegym.hpn.model.User;
import com.codegym.hpn.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    List<UserDTO> findAllUserDTO();

    List<UserDTO> findAllAdminDTO(Long id);

    Optional<User> findByIdAndDeletedFalse(Long id);

    void blockUser(Long id);

    void unblockUser(Long id);

    boolean existsByUsernameAndIdIsNot(String username, Long id);


}

