package com.codegym.hpn.model.dto;

import com.codegym.hpn.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codegym.hpn.utils.ValidationUtils.EMAIL_REGEX;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Email không hợp lệ.")
    @Size(min = 5, max = 50, message = "Độ dài của email phải từ 5 đến 50 ký tự.")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống.")
    @Size(min = 6, message = "Độ dài mật khẩu tối thiểu là 6 ký tự.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$",
            message = "Mật khẩu: Tối thiểu sáu ký tự, ít nhất một chữ cái, một số và một ký tự đặc biệt:")
    private String password;

    @NotBlank(message = "Họ và tên không được để trống.")
    @Pattern(regexp = "[A-Za-z\\s]+", message = "Họ và Tên chỉ chứa các chữ cái và khoảng trắng.")
    private String fullName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",
            message = "Định dạng số điện thoại không chính xác. Định dạng đúng: +1 (608) 468-6527.")
    private String phoneNumber;

    private String address;

    private String roleName;

    private boolean deleted;

    @Valid
    private RoleDTO role;

    private String creationDate;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(Long id, String username, String fullName, String phoneNumber, String address, String roleName, Date creationDate, boolean deleted) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleName = roleName;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss a");
        this.creationDate = dateFormat.format(creationDate);
        this.deleted = deleted;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setRole(role.toRole());
    }

}

