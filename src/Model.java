import java.util.ArrayList;

/**
 * Model that acts as a data structure for the gamebook, containing its pages
 * and links. Has methods for interfacing with this data.
 */
public class Model {
    private ArrayList<Page> pages;
    private int currentPageId;

    /**
     * Constructs a blank model
     */
    public Model() {
        this.pages = new ArrayList<>();
    }

    /**
     * @param id page id to find
     * @return the page with the given id, or null of none is found
     */
    public Page getPage(int id) {
        for (Page page : this.pages) {
            if (page.getId() == id) return page;
        }
        return null;
    }

    /**
     * Creates a new page and appends it to the model
     * @param id unique identifier for use in database
     * @return the newly created page
     */
    public Page createPage(int id) {
        Page page = new Page(id);
        this.pages.add(page);
        return page;
    }

    /**
     * @return all pages in the model
     */
    public ArrayList<Page> getPages() {
        return pages;
    }

    /**
     * @param pages pages to overwrite the model with
     */
    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    /**
     * @return id of the page that is currently shown in the player or editor
     */
    public int getCurrentPageId() {
        return currentPageId;
    }

    /**
     * @param currentPageId id of the page that is currently shown in the
     *                      player or editor
     */
    public void setCurrentPageId(int currentPageId) {
        this.currentPageId = currentPageId;
    }
}
