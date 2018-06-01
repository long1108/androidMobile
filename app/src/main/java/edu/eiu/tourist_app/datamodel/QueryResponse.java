package edu.eiu.tourist_app.datamodel;

import java.util.Map;

public class QueryResponse {
    private Map<Integer, WikipediaPage> pages;

    public Map<Integer, WikipediaPage> getPages() {
        return pages;
    }

    public void setPages(Map<Integer, WikipediaPage> pages) {
        this.pages = pages;
    }
}
