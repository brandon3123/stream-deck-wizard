package model.routine.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoutineSettings {
    private boolean openInBrowser;
    private String path;
    private long delay;

    public RoutineSettings(boolean openInBrowser, String path, long delay) {
        this.openInBrowser = openInBrowser;
        this.path = path;
        this.delay = delay;
    }

    public RoutineSettings(long delay) {
        this.delay = delay;
    }

    public boolean isOpenInBrowser() {
        return openInBrowser;
    }

    public void setOpenInBrowser(boolean openInBrowser) {
        this.openInBrowser = openInBrowser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "RoutineSettings{" +
                "openInBrowser=" + openInBrowser +
                ", path='" + path + '\'' +
                ", delay=" + delay +
                '}';
    }
}