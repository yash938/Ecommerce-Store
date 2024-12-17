package com.Ecommerce.web.application.Repository;

import com.Ecommerce.web.application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public List<User> findByFullNameContaining(String keyword);

    Optional<User> findByEmail(String email);
}
