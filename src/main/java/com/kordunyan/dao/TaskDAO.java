package com.kordunyan.dao;

import com.kordunyan.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, Long> {
}
