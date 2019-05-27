package mk.edu.uklo.fikt.fiktexamweb.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import mk.edu.uklo.fikt.fiktexamweb.model.Subject;
import mk.edu.uklo.fikt.fiktexamweb.util.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import mk.edu.uklo.fikt.fiktexamweb.model.User;
import mk.edu.uklo.fikt.fiktexamweb.util.UserService;

//@RestController
@Controller
@RequestMapping({"/user"})
@SessionAttributes
public class UserController {

    @Autowired
    UserService userService;

    //show createStudent.html
    @GetMapping("/addstudent")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createStudent";
    }

    //show createTeacher.html
    @GetMapping("/addteacher")
    public String showTeacherForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createTeacher";
    }

    //show adminui.html
    @GetMapping("/adminform")
    public String showAdminForm(Model model) {
        User user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("imeprezime", user.getImePrezime());
        return "adminui";
    }

    //show login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    //create a teacher
    @PostMapping({"/post/teacher"})
    public String addTeacher(@Valid @RequestBody @ModelAttribute(value = "user") User user, Model model) {
        model.addAttribute("user", user);
        userService.createTeacher(user);
        return "adminui";
    }

    //create a student
    @PostMapping({"/post/student"})
    public String addStudent(@Valid @RequestBody @ModelAttribute(value = "user") User user, Model model) {
        model.addAttribute("user", user);
        userService.createStudent(user);
        return "adminui";
    }
}
