package am.itspace.companyemployeespring.controller;

import am.itspace.companyemployeespring.entity.User;
import am.itspace.companyemployeespring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String users() {
        return "user";
    }

    @GetMapping("/users")
    public String users(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/add")
    public String addUserPage() {
        return "addUser";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute User user) throws IOException {
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/users/delete")
    public String delete(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
