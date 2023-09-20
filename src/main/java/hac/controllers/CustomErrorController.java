package hac.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * this controller handles all exceptions/errors and displays a friendly error page.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * get the error page.
     * @param model - the model
     * @return the error page
     */
    @RequestMapping("/errorPage")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "Some error occured");
        return "error";
    }

    /**
     * get the access denied page.
     * @return the access denied page
     */
    @RequestMapping("/403")
    public String handleAccessDenied() {
        return "403";
    }
}