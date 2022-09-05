package com.codegym.hpn.service.impl;

import com.codegym.hpn.model.User;
import com.codegym.hpn.model.UserPrinciple;
import com.codegym.hpn.model.dto.UserDTO;
import com.codegym.hpn.repository.UserRepository;
import com.codegym.hpn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public List<UserDTO> findAllAdminDTO(Long id) {
        return userRepository.findAllAdminDTO(id);
    }

    @Override
    public Optional<User> findByIdAndDeletedFalse(Long id) {
        return userRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
//        return (UserDetails) userOptional.get();
    }

    @Override
    public void blockUser(Long id) {
        userRepository.blockUser(id);
    }

    @Override
    public void unblockUser(Long id) {
        userRepository.unblockUser(id);
    }

    public boolean existsByUsernameAndIdIsNot(String username, Long id) {
        return userRepository.existsByUsernameAndIdIsNot(username, id);
    }


}

