package com.codegym.hpn.controller;

import com.codegym.hpn.model.User;
import com.codegym.hpn.model.dto.UserDTO;
import com.codegym.hpn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    private String getPrincipalName() {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            Optional<User> currentUser = userService.findByUsername(username);
            username = currentUser.get().getFullName();
        } else {
            username = principal.toString();
        }

        return username;
    }

    private UserDTO getPrincipal() {
        UserDTO userDTO;
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = ((UserDetails) principal).getUsername();
        Optional<User> currentUser = userService.findByUsername(username);
        userDTO = currentUser.get().toUserDTO();
        return userDTO;
    }


    @GetMapping
    public ModelAndView showUserList() {
        ModelAndView modelAndView = new ModelAndView("/user-management/list");
        modelAndView.addObject("username", getPrincipalName());
        return modelAndView;
    }

    @GetMapping("/admins")
    public ModelAndView showAdminList() {
        ModelAndView modelAndView = new ModelAndView("/admin-management/list");
        modelAndView.addObject("username", getPrincipalName());
        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView showUserDetail() {
        ModelAndView modelAndView = new ModelAndView("/user-management/detail");
        modelAndView.addObject("username", getPrincipalName());
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }

    @PostMapping("/detail")
    public ModelAndView updateUserInfo(@ModelAttribute("user") UserDTO userDTO,
                                       BindingResult bindingResult) {

        //Validation is not applied to userDTO
        ModelAndView modelAndView = new ModelAndView("/user-management/detail");
        try {
            UserDTO oldUserDTO = getPrincipal();
            Long userId = oldUserDTO.getId();
            User oldUser = userService.findById(userId).get();

            String username = userDTO.getUsername().trim().toLowerCase();


            oldUser.setFullName(userDTO.getFullName().trim());
            oldUser.setPhoneNumber(userDTO.getPhoneNumber().trim());
            oldUser.setUsername(username);
            oldUser.setAddress(userDTO.getAddress().trim());

            if (userService.existsByUsernameAndIdIsNot(username, userId)) {
                modelAndView.addObject("error","Email đã tồn tại!");
                modelAndView.addObject("user",oldUser.toUserDTO());
                return modelAndView;
            }

            userService.save(oldUser);
            modelAndView.addObject("user",oldUser.toUserDTO());
            modelAndView.addObject("success","Cập nhật thông tin thành công!");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject("user",new UserDTO());
            modelAndView.addObject("error","Đã xảy ra lỗi không mong muốn! Vui lòng thử lại.");
            return modelAndView;
        }

    }


}

