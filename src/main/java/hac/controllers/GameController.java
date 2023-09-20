package hac.controllers;

import hac.RevealedWord;
import hac.repo.Entry;
import hac.repo.EntryRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GameController class - the controller to handle the game logic.
 */
@Controller
@RequestMapping("/game")
public class GameController {
    /**
     * the number of tries the user has.
     */
    private final int NUM_OF_TRIES = 7;
    /**
     * the maximum score the user can get.
     */
    private final int MAX_SCORE = 10;

    /**
     * the entry repository (contains all categories and words).
     */
    @Autowired
    private EntryRepository entryRepository;

    /**
     * the current category+word the user has to guess.
     */
    @Resource(name = "sessionEntry")
    private Entry entry;

    /**
     * the current revealed word by the user.
     * for example, if the word is lion and the user guessed l and i, the revealed word will be li__.
     */
    @Resource(name = "sessionRevealedWord")
    private RevealedWord revealedWord;

    /**
     * sessionKeyboard - the keyboard stored in the session (per user)
     * the keyboard maps every letter to one of 3 states:
     * blue = "not-guessed"
     * green = "gussed correctly"
     * red =  "guessed wrong"
     */
    @Resource(name = "sessionKeyboard")
    private Map<Character, String> keyboard;

    /**
     * the number of wrong guesses stored in the session (per user)
     */
    @Resource(name = "sessionMisses")
    private AtomicInteger misses;


    /**
     * get the main page of the game.
     * select a random word from the db, and initialize the game states.
     * @param model - the model
     * @return the main page of the game
     */
    @GetMapping("")
    public String index(Model model) {
        try {
            entry = entryRepository.findRandomEntry();
        } catch (Exception e) {
            throw new RuntimeException("No entries found in the database.");
        }

        revealedWord.setRevealedWord(entry.getWord().replaceAll("[a-zA-Z]", "_"));
        initKeyboard();
        misses.set(0);
        model.addAttribute("category", entry.getCategory());
        model.addAttribute("revealedWord", revealedWord.getRevealedWord().split(""));
        model.addAttribute("keyboard", keyboard);
        model.addAttribute("misses", misses.get());

        return "index";
    }


    /**
     * a post request from the user that guess a specific letter.
     * @param letter the letter the user guessed
     * @param model the model
     * @return the game page with updated keyboard and revealed word, or win/lose page
     */
    @PostMapping("/guess/{letter}")
    public String guessLetter(@PathVariable("letter") Character letter, Model model) {

        // the letter is available for guess:
        if (keyboard.get(letter).equals("blue")) {
            // the user guessed correctly:
            if(entry.getWord().contains(letter.toString())) {
                // update the revealed word:
                for(int i = 0; i < entry.getWord().length(); i++) {
                    if(entry.getWord().charAt(i) == letter) {
                        revealedWord.setRevealedWord(revealedWord.getRevealedWord().substring(0, i) + letter +
                                                     revealedWord.getRevealedWord().substring(i + 1));
                    }
                }
                keyboard.put(letter, "green");
                if(!revealedWord.getRevealedWord().contains("_")) { //the user guessed all the letters correctly
                    model.addAttribute("score", MAX_SCORE - misses.get());
                    return "winPage";
                }

            } else { //the user doesn't guess correctly:
                keyboard.put(letter, "red");
                misses.incrementAndGet();
                if(misses.get() >= NUM_OF_TRIES) {
                    return "losePage";
                }
            }
        }

        model.addAttribute("category", entry.getCategory());
        model.addAttribute("revealedWord", revealedWord.getRevealedWord().split(""));
        model.addAttribute("keyboard", keyboard);
        model.addAttribute("misses", misses.get());

        return "index";
    }

    /**
     * initialize the keyboard to initial state (blue = unused key).
     */
    private void initKeyboard() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            // set all keyboard key to initial state (blue = unused key)
            keyboard.put(letter, "blue");
        }
    }

    /**
     * get the instructions page.
     * @return the instructions page
     */
    @GetMapping("/instructions")
    public String instructions() {
        return "instructions";
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
