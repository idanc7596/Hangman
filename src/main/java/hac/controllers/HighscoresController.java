package hac.controllers;

import hac.repo.Record;
import hac.repo.RecordRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Highscores Controller class - the controller to handle the highscores list.
 */
@Controller
@RequestMapping("/highscores")
public class HighscoresController {
    /**
     * the maximum score the user can get.
     */
    private final int MAX_SCORE = 10;

    /**
     * the record repository (contains all records).
     */
    @Autowired
    private RecordRepository recordRepository;

    /**
     * sessionMisses - the misses (wrong guesses) stored in the session (per user)
     */
    @Resource(name = "sessionMisses")
    private AtomicInteger misses;

    /**
     * highscoresPage - the highscores page (contains the highscores table).
     * @param model the model.
     * @return the highscores page.
     */
    @GetMapping("")
    public String highscoresPage(Model model) {
        model.addAttribute("records", recordRepository.findAllByOrderByScoreDesc());
        return "highscores";
    }

    /**
     * registerHighscore - register the user's highscore.
     * @param principal - the principal (for the name of the user)
     * @return the highscores page
     */
    @PostMapping("/register")
    public String registerHighscore(Principal principal) {
        Record record = recordRepository.findByUsername(principal.getName());
        if(record != null) {
            if(record.getScore() < MAX_SCORE - misses.get()) {
                record.setScore(MAX_SCORE - misses.get());
                recordRepository.save(record);
            }
        } else { //the user doesn't have a record yet
            recordRepository.save(new Record(principal.getName(),
                    MAX_SCORE - misses.get()));
        }
        return "redirect:/highscores";
    }

    /**
     * handleValidationExceptions - handle all exceptions and display a friendly error page.
     * @param ex - the exception
     * @param model - the model
     * @return the error page
     */
    @ExceptionHandler({Exception.class})
    public String handleValidationExceptions(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

}
