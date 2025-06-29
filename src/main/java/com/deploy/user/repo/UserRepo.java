package com.deploy.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deploy.user.entity.AppUser;


@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {

	Optional<AppUser> findByUserName(String username);

}
