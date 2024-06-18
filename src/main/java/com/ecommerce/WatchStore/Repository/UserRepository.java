package com.ecommerce.WatchStore.Repository;

import com.ecommerce.WatchStore.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNumberPhone (String numberPhone);

    Optional<User> findByNumberPhone(String numberPhone);
}
