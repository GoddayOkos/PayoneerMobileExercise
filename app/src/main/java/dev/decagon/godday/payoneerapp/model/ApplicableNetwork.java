package dev.decagon.godday.payoneerapp.model;

import java.net.URL;
import java.util.Map;

public class ApplicableNetwork {
    private String code;
    private String label;
    private Map<String, URL> links;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, URL> getLinks() {
        return links;
    }

    public void setLinks(Map<String, URL> links) {
        this.links = links;
    }
}
