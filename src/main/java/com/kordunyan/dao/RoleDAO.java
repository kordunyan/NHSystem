package com.kordunyan.dao;

import com.kordunyan.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, String> {
}
