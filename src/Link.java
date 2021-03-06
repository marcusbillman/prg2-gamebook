/**
 * A link in the gamebook that allows the player to progress to other pages.
 */
public class Link {
    private int id;
    private int fromPageId;
    private int toPageId;
    private String text;

    /**
     * Constructs a link with all properties defined.
     * @param id unique identifier for use in database
     * @param fromPageId id of the page on which the link appears
     * @param toPageId id of the page to which the link points
     * @param text text that is displayed to the user and describes e.g. the
     *             action that the gamebook character takes
     */
    public Link(int id, int fromPageId, int toPageId, String text) {
        this.id = id;
        this.fromPageId = fromPageId;
        this.toPageId = toPageId;
        this.text = text;
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
     * @return id of the page on which the link appears
     */
    public int getFromPageId() {
        return fromPageId;
    }

    /**
     * @param fromPageId id of the page on which the link appears
     */
    public void setFromPageId(int fromPageId) {
        this.fromPageId = fromPageId;
    }

    /**
     * @return id of the page to which the link points
     */
    public int getToPageId() {
        return toPageId;
    }

    /**
     * @param toPageId id of the page to which the link points
     */
    public void setToPageId(int toPageId) {
        this.toPageId = toPageId;
    }

    /**
     * @return text that is displayed to the user and describes e.g. the
     * action that the gamebook character takes
     */
    public String getText() {
        return text;
    }

    /**
     * @param text text that is displayed to the user and describes e.g. the
     *             action that the gamebook character takes
     */
    public void setText(String text) {
        this.text = text;
    }
}
