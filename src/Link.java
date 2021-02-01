// TODO: Write Javadoc

public class Link {
    private int id;
    private int fromPageId;
    private int toPageId;
    private String text;

    public Link(int id, int fromPageId, int toPageId, String text) {
        this.id = id;
        this.fromPageId = fromPageId;
        this.toPageId = toPageId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromPageId() {
        return fromPageId;
    }

    public void setFromPageId(int fromPageId) {
        this.fromPageId = fromPageId;
    }

    public int getToPageId() {
        return toPageId;
    }

    public void setToPageId(int toPageId) {
        this.toPageId = toPageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
