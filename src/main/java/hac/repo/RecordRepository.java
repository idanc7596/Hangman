package hac.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * RecordRepository interface - create a repository for records.
 */
public interface RecordRepository extends JpaRepository<Record, Long> {
    /**
     * findByUsername - find a record by username
     * @param name - the username
     * @return - the record found
     */
    Record findByUsername(String name);

    /**
     * findAllByOrderByScoreDesc - find all records ordered by score descending
     * in order to show the highscores sorted
     * @return - the records found
     */
    List<Record> findAllByOrderByScoreDesc();
}