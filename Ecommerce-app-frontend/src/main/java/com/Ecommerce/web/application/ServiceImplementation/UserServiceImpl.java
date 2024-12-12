package com.Ecommerce.web.application.ServiceImplementation;

import com.Ecommerce.web.application.Model.User;
import com.Ecommerce.web.application.Repository.UserRepo;
import com.Ecommerce.web.application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public User createUser(User user) {
        User save = userRepo.save(user);
        return save;
    }

    @Override
    public User findById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User id is not found"));
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        User existUser = findById(id);
        existUser.setEmail(user.getEmail());
        existUser.setMobile(user.getMobile());
        existUser.setFullName(user.getFullName());
        existUser.setPassword(user.getPassword());
        existUser.setRole(user.getRole());
        User updateUsers = userRepo.save(existUser);
        return updateUsers;
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepo.delete(user);
    }

    @Override
    public List<User> allUser(Long pageNumber, Long pageSize, String sortBy, String sortDir) {
        return null;
    }
}
