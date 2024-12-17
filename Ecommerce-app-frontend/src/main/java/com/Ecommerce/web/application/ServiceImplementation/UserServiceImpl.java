package com.Ecommerce.web.application.ServiceImplementation;

import com.Ecommerce.web.application.Dto.PaegableResponse;
import com.Ecommerce.web.application.Dto.UserDto;
import com.Ecommerce.web.application.Model.User;
import com.Ecommerce.web.application.Repository.UserRepo;
import com.Ecommerce.web.application.Service.UserService;
import com.Ecommerce.web.application.Utility.Helper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        log.info("User created with : {}", savedUser.getUsername());

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        User userId = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User id is not found"));
        UserDto user = modelMapper.map(userId, UserDto.class);
        return user;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UserDto userId = findById(id);
        userId.setFullName(userDto.getFullName());
        userId.setMobile(userDto.getMobile());
        userId.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userId.setEmail(userDto.getEmail());
        userId.setRole(userDto.getRole());
        userId.setAddresses(userDto.getAddresses());

        return modelMapper.map(userId,UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User userId = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User is deleted"));
        userRepo.delete(userId);
    }

    @Override
    public PaegableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepo.findAll(pageable);
        PaegableResponse<UserDto> paegable = Helper.getPaegable(page, UserDto.class);
        return paegable;
    }

    public List<UserDto> searchUser(String keyword){
        List<User> user = userRepo.findByFullNameContaining(keyword);
        List<UserDto> searhedUser = user.stream().map(users -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return searhedUser;
    }
}
