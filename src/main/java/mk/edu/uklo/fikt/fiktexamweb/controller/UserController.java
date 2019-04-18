package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;
import java.util.Optional;

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


	//maybe won't be used
	@GetMapping({"/get"})
	public List<User> getAllUsers(){
		return userBl.getAllUsers();
	}
	
	@PostMapping({"/post/teacher"})
	public User addTeacher(@Valid @RequestBody User user) {
		return userBl.createTeacher(user);
	}
	
	@PostMapping({"/post/student"})
	public User addStudent(@Valid @RequestBody User user) {
		return userBl.createStudent(user);
	}


	//maybe needs to be deleted
	@GetMapping("get/asd")
	public Optional<User> getByUsername(String username){
		return userBl.lista(username);
	}
}
