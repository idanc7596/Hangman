package hac.repo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Entry class - represents an entry for the game (category and word).
 */
@Entity
public class Entry implements Serializable {
    /**
     * id - entry id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * category - category of the entry
     */
    @NotEmpty(message = "Category is mandatory")
    private String category;

    /**
     * word - word of the entry
     */
    @NotEmpty(message = "Word is mandatory")
    private String word;

    /**
     * Entry ctor - default
     */
    public Entry() {}

    /**
     * Entry ctor - with parameters
     * @param category - category of the entry
     * @param word - word of the entry
     */
    public Entry(String category, String word) {
        this.category = category;
        this.word = word.toUpperCase();
    }

    /**
     * getId - returns the id of the entry
     * @return id - id of the entry
     */
    public long getId() {
        return id;
    }

    /**
     * getCategory - returns the category of the entry
     * @return category - category of the entry
     */
    public String getCategory() {
        return category;
    }

    /**
     * getWord - returns the word of the entry
     * @return word - word of the entry
     */
    public String getWord() {
        return word;
    }

    /**
     * setId - sets the id of the entry
     * @param id - id of the entry
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * setCategory - sets the category of the entry
     * @param category - category of the entry
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * setWord - sets the word of the entry
     * @param word - word of the entry
     */
    public void setWord(String word) {
        this.word = word;
    }

}
