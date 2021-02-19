package com.metkal.taskit.dao;

import java.util.List;

import com.metkal.taskit.model.Task;
import com.metkal.taskit.model.User;

public interface TaskDao {
	
	List<Task> getTasks(long userId);
	
	Task getTask(long id);
	
	Task addTask(Task task);
	
	Task updateTask(Task task);
	
	boolean deleteTask(long id);
	
	boolean deleteTasks(long userId);
	
	boolean shareTask(String email, Task task);
	
}
