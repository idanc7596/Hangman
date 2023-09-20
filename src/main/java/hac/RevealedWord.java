package hac;

/**
 * RevealedWord class - the revealed word currently guessed by the user
 */
public class RevealedWord implements java.io.Serializable {

    /**
     * revealedWord - the revealed word
     */
    private String revealedWord;

    /**
     * RevealedWord ctor - set the revealed word to empty string
     */
    public RevealedWord() {
        revealedWord = "";
    }

    /**
     * getRevealedWord - get the revealed word
     * @return - the revealed word
     */
    public String getRevealedWord() {
        return revealedWord;
    }

    /**
     * setRevealedWord - set the revealed word
     * @param revealedWord - the revealed word
     */
    public void setRevealedWord(String revealedWord) {
        this.revealedWord = revealedWord;
    }
}
