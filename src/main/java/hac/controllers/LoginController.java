package hac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller handles the login page
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * get the login page
     * @return the login page
     */
    @GetMapping("")
    public String login() {
        return "login";
    }

}
