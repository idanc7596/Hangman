package hac.configurations;
import hac.RevealedWord;
import hac.repo.Entry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * BeanConfiguration class - configure the beans
 */
@Configuration
public class BeanConfig {

    /**
     * sessionEntry - the entry (category + word) stored in the session (per user)
     * @return - the entry
     */
    @Bean
    @SessionScope
    public Entry sessionEntry() {
        return new Entry();
    }

    /**
     * sessionRevealedWord - the revealed word stored in the session (per user)
     * @return - the revealed word
     */
    @Bean
    @SessionScope
    public RevealedWord sessionRevealedWord() {
        return new RevealedWord();
    }

    /**
     * sessionKeyboard - the keyboard stored in the session (per user)
     * the keyboard maps every letter to one of 3 states:
     * blue = "not-guessed"
     * green = "gussed correctly"
     * red =  "guessed wrong"
     * @return - the keyboard map for this session
     */
    @Bean
    @SessionScope
    public Map<Character, String> sessionKeyboard() {
        return new HashMap<>();
    }


    /**
     * sessionMisses - the number of wrong guesses stored in the session (per user)
     * @return - the number of misses
     */
    @Bean
    @SessionScope
    public AtomicInteger sessionMisses() {
        return new AtomicInteger(0);
    }

}
