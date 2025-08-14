package com.example.store.repositories;

import com.example.store.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@NotBlank(message = "email is required") @Email(message = "email must be valid") String email);

    Optional<User> findByEmail(@NotBlank(message = "email cannot be blank") @Email String email);
}
