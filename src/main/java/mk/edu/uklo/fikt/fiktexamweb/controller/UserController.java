package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.UserBL;

//@RestController
@Controller
@RequestMapping({"/user"})
@SessionAttributes
public class UserController {
	
	@Autowired
	UserBL userBl;

	//show createStudent.html
	@GetMapping("/addstudent")
	public String showForm(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "createStudent";
	}

	//show createTeacher.html
	@GetMapping("/addteacher")
	public String showTeacherForm(Model model){
		User user = new User();
		model.addAttribute("user", user);
		return "createTeacher";
	}

	//show adminui.html
	@GetMapping("/adminform")
	public String showAdminForm(){
		return "adminui";
	}

	//show login form
	@GetMapping("/login")
	public String showLoginForm(){
		return "login";
	}


	//maybe won't be used
	@GetMapping({"/get"})
	public List<User> getAllUsers(){
		return userBl.getAllUsers();
	}
	
	@PostMapping({"/post/teacher"})
	public String addTeacher(@Valid @RequestBody @ModelAttribute(value = "user") User user, Model model) {
		model.addAttribute("user", user);
		userBl.createTeacher(user);
		return "adminui";
	}

	@PostMapping({"/post/student"})
	public String addStudent(@Valid @RequestBody @ModelAttribute(value = "user") User user, Model model) {
		model.addAttribute("user", user);
		userBl.createStudent(user);
		return "adminui";
	}




	//maybe needs to be deleted
	@GetMapping("get/asd")
	public Optional<User> getByUsername(String username){
		return userBl.lista(username);
	}
}
