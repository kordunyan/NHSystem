package com.kordunyan.dao;

import com.kordunyan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, String> {

}
