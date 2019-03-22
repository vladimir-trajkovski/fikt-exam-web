package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.UserBL;

@RestController
@RequestMapping({"/user"})
public class UserController {
	
	@Autowired
	UserBL userBl;
	
	@GetMapping({"/get"})
	public List<User> getAllUsers(){
		return userBl.getAllUsers();
	}
	
	@GetMapping({"/get/login"})
	public List<User> getUsersByUsername(String username, String password){
		return userBl.getUserByUsername(username, password);
	}
	
	@PostMapping({"/post"})
	public User addUser(@Valid @RequestBody User user) {
		return userBl.createUser(user);
	}

}
