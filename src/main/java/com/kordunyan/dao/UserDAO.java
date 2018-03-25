package com.kordunyan.dao;

import com.kordunyan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, String> {

	List<User> findByNameLike(String name);
}
