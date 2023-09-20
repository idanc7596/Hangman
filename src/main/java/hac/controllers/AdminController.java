package hac.controllers;

import hac.repo.Entry;
import hac.repo.EntryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *  admin controller - the controller that manages the entries database - add entry, delete entry, etc.
 *  note that this controller is only accessible to the admin user.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * The repository for the entries (category + word)
     */
    @Autowired
    private EntryRepository entryRepository;

    /**
     * get the admin page - this page displays all the entries in the database
     * @param entry the entry
     * @param model the model
     * @return the admin page
     */
    @GetMapping("")
    public String adminPage(Entry entry, Model model) {
        model.addAttribute("entries", entryRepository.findAll());
        return "admin";
    }

    /**
     * add a new entry to the database
     * @param entry the entry to add (category + word)
     * @param result the result of the database validation
     * @param model the model
     * @return the admin page
     */
    @PostMapping("/addEntry")
    public String addEntry(@Valid Entry entry, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("entries", entryRepository.findAll());
            return "admin";
        }
        entry.setWord(entry.getWord().toUpperCase());
        entryRepository.save(entry);
        return "redirect:/admin";
    }

    /**
     * delete an entry from the database
     * @param id the id of the entry to delete
     * @return the admin page
     */
    @PostMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable("id") long id) {
        Entry entry = entryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid entry ID: " + id)
        );
        entryRepository.delete(entry);
        return "redirect:/admin";
    }

    /**
     * handle all exceptions/errors and display a friendly error page
     * @param ex the exception
     * @param model the model
     * @return the error page
     */
    @ExceptionHandler({Exception.class})
    public String handleValidationExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
