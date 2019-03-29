package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.User;

@Service
public class UserBL{
	
	@Autowired
	UserRepository userRepository;
	
	
	//Get all Users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	
	
	//Get a user with by username and password
	public List<User> getUserByUsername(String username, String password){
		
		User example = new User();
		
		example.setPassword(password);
		example.setUsername(username);
		
		
		return userRepository.findAll(Example.of(example));
	}
	
	//get user by username only
	public List<User> getByUsername(String username){
		User example = new User();
		
		example.setUsername(username);
		
		return userRepository.findAll(Example.of(example));
	}
	
	
	//Create new user - teacher
	public User createTeacher(User user) {
		user.setBrIndex(null);
		user.setRole("Teacher");
		user.setToken("123321");
		return userRepository.save(user);
	}
	
	//Create new user - Student
	public User createStudent(User user) {
		user.setRole("Student");
		user.setToken("123321");
		return userRepository.save(user);
	}
	
}
