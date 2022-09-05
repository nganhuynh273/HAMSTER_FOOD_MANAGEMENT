package com.codegym.hpn.controller.api;

import com.codegym.hpn.model.User;
import com.codegym.hpn.model.dto.UserDTO;
import com.codegym.hpn.service.IUserService;
import com.codegym.hpn.utils.ParsingValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserDTO> userDTOList = userService.findAllUserDTO();

            if (userDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userDTOList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admins")
    public ResponseEntity<?> getAllAdmins() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            Optional<User> currentUser = userService.findByUsername(username);

            List<UserDTO> userDTOList = userService.findAllAdminDTO(currentUser.get().getId());

            if (userDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userDTOList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findUser/{userId}")
    public ResponseEntity<?> findMaterialById(@PathVariable String userId) {
        if (ParsingValidationUtils.isLongParsable(userId)) {
            long validId = Long.parseLong(userId);
            Optional<User> user = userService.findById(validId);

            if (user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User không tồn tại.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/blockUser/{userId}")
    public ResponseEntity<?> blockUser(@PathVariable String userId) {
        return blockOrUnblockUser(userId, "block");
    }

    @PutMapping("/unblockUser/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable String userId) {
        return blockOrUnblockUser(userId, "unblock");
    }

    private ResponseEntity<?> blockOrUnblockUser(String userId, String action) {
        if (ParsingValidationUtils.isLongParsable(userId)) {
            long validId = Long.parseLong(userId);
            Optional<User> user = userService.findById(validId);

            if (user.isPresent()) {
                try {
                    switch (action) {
                        case "block":
                            userService.blockUser(validId);
                            return new ResponseEntity<>(HttpStatus.OK);
                        case "unblock":
                            userService.unblockUser(validId);
                            return new ResponseEntity<>(HttpStatus.OK);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>("Server error!", HttpStatus.NOT_FOUND);
                }
            }
        }

        return new ResponseEntity<>("User không tồn tại.", HttpStatus.NOT_FOUND);
    }
}
