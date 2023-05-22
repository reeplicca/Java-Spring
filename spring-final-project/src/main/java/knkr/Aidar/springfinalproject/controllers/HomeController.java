package knkr.Aidar.springfinalproject.controllers;

import knkr.Aidar.springfinalproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/login")
    public String openLogin() {
        return "login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/home")
    public String openHome() {
        return "home";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/profile")
    public String openProfile() {
        return "profile";
    }

    @GetMapping(value = "/register")
    public String openRegister() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerPost(@RequestParam(name = "user-full-name") String fullname,
                               @RequestParam(name = "user-email") String email,
                               @RequestParam(name = "user-password") String password,
                               @RequestParam(name = "user-re-password") String rePassword) {
        String redirect = "";

        String check = clientService.registerUser(fullname,email,password,rePassword);

        if (check.equals("registerSuccess")) {
            redirect = "/register?success";
        } else if (check.equals("passwordNotMatch")) {
            redirect = "/register?passwordNotMatch";
        } else {
            redirect = "/register?userExist";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/403")
    public String open403() {
        return "403";
    }
}
