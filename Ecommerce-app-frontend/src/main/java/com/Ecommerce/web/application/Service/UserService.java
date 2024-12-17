package com.Ecommerce.web.application.Service;

import com.Ecommerce.web.application.Dto.PaegableResponse;
import com.Ecommerce.web.application.Dto.UserDto;


import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto findById(Long id);

    public UserDto updateUser(Long id,UserDto userDto);

    public void deleteUser(Long id);
    PaegableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    public List<UserDto> searchUser(String keyword);
}
