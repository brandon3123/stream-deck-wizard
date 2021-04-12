package model.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class State {

    private String fFamily = "";
    private String fSize = "";
    private String fStyle = "";
    private String fUnderline = "";
    private String image = "";
    private String title = "";
    private String titleAlignment = "";
    private String titleColor = "";
    private String titleShow = "";

    public State() {

    }

    public State(String titleAlignment) {
        this.titleAlignment = titleAlignment;
    }

    public State(String fFamily, String fSize, String fStyle, String fUnderline, String image, String title, String titleAlignment, String titleColor, String titleShow) {
        this.fFamily = fFamily;
        this.fSize = fSize;
        this.fStyle = fStyle;
        this.fUnderline = fUnderline;
        this.image = image;
        this.title = title;
        this.titleAlignment = titleAlignment;
        this.titleColor = titleColor;
        this.titleShow = titleShow;
    }

    public String getfFamily() {
        return fFamily;
    }

    public void setfFamily(String fFamily) {
        this.fFamily = fFamily;
    }

    public String getfSize() {
        return fSize;
    }

    public void setfSize(String fSize) {
        this.fSize = fSize;
    }

    public String getfStyle() {
        return fStyle;
    }

    public void setfStyle(String fStyle) {
        this.fStyle = fStyle;
    }

    public String getfUnderline() {
        return fUnderline;
    }

    public void setfUnderline(String fUnderline) {
        this.fUnderline = fUnderline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAlignment() {
        return titleAlignment;
    }

    public void setTitleAlignment(String titleAlignment) {
        this.titleAlignment = titleAlignment;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleShow() {
        return titleShow;
    }

    public void setTitleShow(String titleShow) {
        this.titleShow = titleShow;
    }

    @Override
    public String toString() {
        return "State{" +
                "fFamily='" + fFamily + '\'' +
                ", fSize='" + fSize + '\'' +
                ", fStyle='" + fStyle + '\'' +
                ", fUnderline='" + fUnderline + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", titleAlignment='" + titleAlignment + '\'' +
                ", titleColor='" + titleColor + '\'' +
                ", titleShow='" + titleShow + '\'' +
                '}';
    }
}