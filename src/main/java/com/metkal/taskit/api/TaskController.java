package com.metkal.taskit.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metkal.taskit.model.Task;
import com.metkal.taskit.service.TaskService;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/user/{id}")
	public List<Task> getTasks(@PathVariable("id") long userId){
		return taskService.getTasks(userId);
	}
	
	@GetMapping("/{id}")
	public Task getTask(@PathVariable("id") long id) {
		return taskService.getTask(id);
	}
	
	@PostMapping
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteTask(@PathVariable("id") long id) {
		return taskService.deleteTask(id);
	}
	
	@DeleteMapping("user/{id}")
	public boolean deleteTasks(@PathVariable("id") long userId) {
		return taskService.deleteTasks(userId);
	}
	
	@PutMapping("share/{email}")
	public boolean shareTask(@PathVariable("email") String email, @RequestBody Task task) {
		return taskService.shareTask(email, task);
	}
}
