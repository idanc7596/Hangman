package hac.listeners;
import hac.repo.Entry;
import hac.repo.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

/**
 * ContextListener class - listen to context events (start/refresh of application events).
 */
@Component
public class ContextListener {

    /**
     * entryRepository - the repository for entries (categories+ words)
     */
    @Autowired
    private EntryRepository entryRepository;

    /**
     * handleContextStarted - handle the context started event - add some entries to the database
     * @param event the context started event
     */
    @EventListener
    public void handleContextStarted(final ContextRefreshedEvent event) {
        entryRepository.save(new Entry("furnitures", "chair"));
        entryRepository.save(new Entry("furnitures", "sofa"));
        entryRepository.save(new Entry("animals", "tiger"));
        entryRepository.save(new Entry("animals", "lion"));
        entryRepository.save(new Entry("fruits", "banana"));
        entryRepository.save(new Entry("fruits", "avocado"));
        entryRepository.save(new Entry("fruits", "pineapple"));
        entryRepository.save(new Entry("vegetables", "tomato"));
        entryRepository.save(new Entry("vegetables", "cucumber"));
        entryRepository.save(new Entry("vegetables", "cabbage"));
        entryRepository.save(new Entry("clothes", "shirt"));
        entryRepository.save(new Entry("clothes", "coat"));
        entryRepository.save(new Entry("countries", "israel"));
        entryRepository.save(new Entry("countries", "united kingdom"));
        entryRepository.save(new Entry("cities", "jerusalem"));
        entryRepository.save(new Entry("zodiac", "taurus"));
        entryRepository.save(new Entry("movies", "harry potter"));
        entryRepository.save(new Entry("religions", "judaism"));
        entryRepository.save(new Entry("shapes", "triangle"));
        entryRepository.save(new Entry("vehicles", "train"));
        entryRepository.save(new Entry("sports", "football"));
    }
}
