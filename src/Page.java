import java.util.ArrayList;

/**
 * A page in the gamebook, containing body text and links that allow the
 * player to progress to other pages.
 */
public class Page {
    private int id;
    private String body;
    private ArrayList<Link> links;
    private boolean isEnding;

    /**
     * Constructs a page with all properties defined.
     * @param id unique identifier for use in database
     * @param body body text that is shown to the player along with the links
     * @param links links allowing the player to progress to other pages
     * @param isEnding whether to treat this page as an ending of the gamebook
     */
    public Page(int id, String body, ArrayList<Link> links, boolean isEnding) {
        this.id = id;
        this.body = body;
        this.links = links;
        this.isEnding = isEnding;
    }

    /**
     * Constructs a page with no links defined.
     * @param id unique identifier for use in database
     * @param body body text that is shown to the player along with the links
     * @param isEnding whether to treat this page as an ending of the gamebook
     */
    public Page(int id, String body, boolean isEnding) {
        this.id = id;
        this.body = body;
        this.links = new ArrayList<>();
        this.isEnding = isEnding;
    }

    /**
     * Constructs a blank page with default property values.
     * @param id unique identifier for use in database
     */
    public Page(int id) {
        this.id = id;
        this.body = "";
        this.links = new ArrayList<>();
        this.isEnding = false;
    }

    /**
     * @return unique identifier for use in database
     */
    public int getId() {
        return id;
    }

    /**
     * @param id unique identifier for use in database
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return body text that is shown to the player along with the links
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body body text that is shown to the player along with the links
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return links allowing the player to progress to other pages
     */
    public ArrayList<Link> getLinks() {
        return links;
    }

    /**
     * @param links links allowing the player to progress to other pages
     */
    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    /**
     * @return whether to treat this page as an ending of the gamebook
     */
    public boolean isEnding() {
        return isEnding;
    }

    /**
     * @param isEnding whether to treat this page as an ending of the gamebook
     */
    public void setEnding(boolean isEnding) {
        this.isEnding = isEnding;
    }
}
