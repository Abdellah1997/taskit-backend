package com.metkal.taskit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metkal.taskit.dao.TaskDao;
import com.metkal.taskit.model.Task;
import com.metkal.taskit.model.User;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Transactional
	public List<Task> getTasks(long userId){
		return taskDao.getTasks(userId);
	}
	
	@Transactional
	public Task getTask(long id) {
		return taskDao.getTask(id);
	}
	
	@Transactional
	public Task addTask(Task task) {
		return taskDao.addTask(task);
	}
	
	@Transactional
	public Task updateTask(Task task) {
		return taskDao.updateTask(task);
	}
	
	@Transactional
	public boolean deleteTask(long id) {
		return taskDao.deleteTask(id);
	}
	
	@Transactional
	public boolean deleteTasks(long userId) {
		return taskDao.deleteTasks(userId);
	}
	
	@Transactional
	public boolean shareTask(String email, Task task) {
		return taskDao.shareTask(email, task);
	}
}
