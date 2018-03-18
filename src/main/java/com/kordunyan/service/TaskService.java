package com.kordunyan.service;

import com.kordunyan.dao.TaskDAO;
import com.kordunyan.domain.Task;
import com.kordunyan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
	@Autowired
	private TaskDAO taskDAO;

	public void addTask(Task task, User user) {
		task.setUser(user);
		taskDAO.save(task);
	}

	public List<Task> findUserTask(User user) {
		return taskDAO.findByUser(user);
	}

}
