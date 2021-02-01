import java.util.ArrayList;

// TODO: Write Javadoc

public class Model {
    private ArrayList<Page> pages;
    private int currentPageId;

    public Model() {
        this.pages = new ArrayList<>();
    }

    public Page getPage(int id) {
        for (Page page : this.pages) {
            if (page.getId() == id) return page;
        }
        return null;
    }

    public Page createPage(int id) {
        Page page = new Page(id);
        this.pages.add(page);
        return page;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public int getCurrentPageId() {
        return currentPageId;
    }

    public void setCurrentPageId(int currentPageId) {
        this.currentPageId = currentPageId;
    }
}
