package mk.edu.uklo.fikt.fiktexamweb.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mk.edu.uklo.fikt.fiktexamweb.model.CustomUserDetails;
import mk.edu.uklo.fikt.fiktexamweb.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optionalUsers = userRepository.findByUsername(username);
		
		return optionalUsers
				.map(CustomUserDetails::new).get();
	}
	
}
