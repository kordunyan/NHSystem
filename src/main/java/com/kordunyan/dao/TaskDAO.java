package com.kordunyan.dao;

import com.kordunyan.domain.Task;
import com.kordunyan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Long> {
	List<Task> findByUser(User user);
}
