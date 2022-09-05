package com.codegym.hpn.repository;

import com.codegym.hpn.model.User;
import com.codegym.hpn.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.codegym.hpn.model.dto.UserDTO (" +
            "u.id, " +
            "u.username) " +
            "FROM User u WHERE u.username = ?1")
    Optional<UserDTO> findUserDTOByUsername(String username);

    @Query("SELECT NEW com.codegym.hpn.model.dto.UserDTO (" +
            "u.id, " +
            "u.username," +
            "u.fullName," +
            "u.phoneNumber," +
            "u.address," +
            "u.role.code," +
            "u.createdAt, " +
            "u.deleted " +
            ") " +
            "FROM User u " +
            "WHERE u.role.id = 1")
    List<UserDTO> findAllUserDTO();

    @Query("SELECT NEW com.codegym.hpn.model.dto.UserDTO (" +
            "u.id, " +
            "u.username," +
            "u.fullName," +
            "u.phoneNumber," +
            "u.address," +
            "u.role.code," +
            "u.createdAt, " +
            "u.deleted " +
            ") " +
            "FROM User u " +
            "WHERE u.role.id = 2 AND u.id <> ?1")
    List<UserDTO> findAllAdminDTO(Long id);

    Optional<User> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u " +
            "SET u.deleted = TRUE " +
            "WHERE u.id = :id")
    void blockUser(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u " +
            "SET u.deleted = FALSE " +
            "WHERE u.id = :id")
    void unblockUser(@Param("id") long id);

    boolean existsByUsernameAndIdIsNot(String username, Long id);
}

