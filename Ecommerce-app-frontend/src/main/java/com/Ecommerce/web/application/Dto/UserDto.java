package com.Ecommerce.web.application.Dto;

import com.Ecommerce.web.application.Domain.USER_ROLE;
import com.Ecommerce.web.application.Model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull(message = "Email is required")
    private String email;
    private String password;
    @NotNull(message = "fullName is required")
    private String fullName;
    private String mobile;
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;
    private Set<Address> addresses = new HashSet<>();
}
