package hac.repo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Record class - represents a record of a user.
 */
@Entity
public class Record implements Serializable {
    /**
     * id - record id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * username - username of the user
     */
    @Column(unique = true)
    @NotEmpty(message = "Username is mandatory")
    private String username;

    /**
     * score - score of the user
     */
    @Range(message = "score must be between 0-10", min = 0, max = 10)
    @Column(nullable = false)
    private int score;

    /**
     * Record ctor - default
     */
    public Record() {}

    /**
     * Record ctor - with parameters
     * @param username - username of the user
     * @param score - score of the user
     */
    public Record(String username, int score) {
        this.username = username;
        this.score = score;
    }

    /**
     * getId - returns the id of the record
     * @return id - the id of the record
     */
    public long getId() {
        return id;
    }

    /**
     * getUsername - returns the username
     * @return username - the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * getScore - returns the score
     * @return score - the score
     */
    public int getScore() {
        return score;
    }

    /**
     * setId - sets the id of the record
     * @param id - the id of the record
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * setUsername - sets the username
     * @param username - the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * setScore - sets the score
     * @param score - the score
     */
    public void setScore(int score) {
        this.score = score;
    }

}


