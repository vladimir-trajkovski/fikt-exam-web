package mk.edu.uklo.fikt.fiktexamweb.security;

import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitialData {
    private static final String ADMIN_ROLE="Admin";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        List<User> allByRole=userRepository.findByRole(ADMIN_ROLE);
        if(allByRole.isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("Admin");
            admin.setId(0);
            admin.setEmail("asd");
            admin.setImePrezime("Admin Admin");
            userRepository.save(admin);
        }
    }
}
