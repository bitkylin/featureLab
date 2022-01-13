package cc.bitky.test.idea.integration.dto;

/**
 * @author limingliang
 */
public class BitkylinConfigState {

    private Boolean initialized;
    private Boolean modified;
    private String path;

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public Boolean getInitialized() {
        return initialized;
    }

    public void setInitialized(Boolean initialized) {
        this.initialized = initialized;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
