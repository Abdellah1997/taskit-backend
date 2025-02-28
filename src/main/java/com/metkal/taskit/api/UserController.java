package com.metkal.taskit.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.metkal.taskit.jwt.JwtUtil;
import com.metkal.taskit.model.User;
import com.metkal.taskit.service.UserService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/authenticate")
	public User getUser(@RequestBody ObjectNode objectNode) throws Exception {
		String email = objectNode.get("email").asText();
		String password = objectNode.get("password").asText();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		}
		catch (BadCredentialsException e) {
			System.out.println("Incorrect username or password");
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails =  userService.loadUserByUsername(email);
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		User user = userService.getUser(email);
		user.setJwt(jwt);
		
		return user;
	}
	
	@PostMapping("/signup")
	public User addUser(@RequestBody User user) throws Exception {
		User userAdded = userService.addUser(user);
		if(userAdded.isValid()) {
			ObjectNode objectNode = new ObjectMapper().createObjectNode();
			objectNode.put("email", userAdded.getEmail());
			objectNode.put("password", userAdded.getPassword());
			System.out.println(userAdded.getPassword());
			return getUser(objectNode);
		}
		return user;
		
	}
	
	@DeleteMapping
	public boolean deleteUser(@RequestBody ObjectNode objectNode) {
		long id = objectNode.get("id").asLong();
		String password = objectNode.get("password").asText();
		return userService.deleteUser(id, password);
	}
	
	@PutMapping("/changePassword")
	public boolean changePassword(@RequestBody ObjectNode objectNode) {
		long id = objectNode.get("id").asLong();
		String password = objectNode.get("password").asText();
		String newPassword = objectNode.get("newPassword").asText();
		
		return userService.changePassword(id, password, newPassword);
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	
	
	

}
