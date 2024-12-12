package com.Ecommerce.web.application.Service;

import com.Ecommerce.web.application.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User createUser(User user);

    public User findById(Long id);

    public User updateUser(Long id,User user);

    public void deleteUser(Long id);
    public List<User> allUser(Long pageNumber, Long pageSize, String sortBy, String sortDir);
}
