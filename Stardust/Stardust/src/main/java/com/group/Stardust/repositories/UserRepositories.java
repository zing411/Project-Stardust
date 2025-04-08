package com.group.Stardust.repositories;

import com.group.Stardust.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories  extends JpaRepository<MyUser, Long> {

    public Optional<MyUser> findByUsername(String username);
}
