package hac.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Random;

/**
 * EntryRepository interface - create a repository for entries.
 */
public interface EntryRepository extends JpaRepository<Entry, Long> {

    /**
     * findRandomEntry - find a random entry for generating a random category+word for the game
     * @return - the random entry
     */
    default Entry findRandomEntry() {
        List<Entry> entries = findAll();
        int randomIndex = new Random().nextInt(entries.size());
        return entries.get(randomIndex);
    }
}