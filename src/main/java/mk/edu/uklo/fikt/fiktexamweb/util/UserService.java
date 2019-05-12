package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Optional<User> lista(String username){
		return userRepository.findByUsername(username);
	}
	
	//Get all Users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	//Create new user - teacher
	public User createTeacher(User user) {
		user.setBrIndex(null);
		user.setRole("Teacher");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	//Create new user - Student
	public User createStudent(User user) {
		user.setRole("Student");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
}
