package model.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang.StringUtils;

@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class State {
    private final String fFamily;
    private final String fSize;
    private final String fStyle;
    private final String fUnderline;
    private final String image;
    private final String title;
    private final String titleAlignment;
    private final String titleColor;
    private final String titleShow;

    public String getfFamily() {
        return fFamily;
    }

    public String getfSize() {
        return fSize;
    }

    public String getfStyle() {
        return fStyle;
    }

    public String getfUnderline() {
        return fUnderline;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleAlignment() {
        return titleAlignment;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getTitleShow() {
        return titleShow;
    }

    public static Builder builder() {
        return new Builder();
    }
    
    private State (Builder builder) {
        this.fFamily = builder.fFamily;
        this.fSize = builder.fSize;
        this.fStyle = builder.fStyle;
        this.fUnderline = builder.fUnderline;
        this.image = builder.image;
        this.title = builder.title;
        this.titleAlignment = builder.titleAlignment;
        this.titleColor = builder.titleColor;
        this.titleShow = builder.titleShow;
    }
    
    public static class Builder {

        /**
         * For some reason the the stream deck manifest files have all these values as empty strings as their default.
         */
        private String fFamily = StringUtils.EMPTY;
        private String fSize = StringUtils.EMPTY;
        private String fStyle = StringUtils.EMPTY;
        private String fUnderline = StringUtils.EMPTY;
        private String image = StringUtils.EMPTY;
        private String title = StringUtils.EMPTY;
        private String titleAlignment = StringUtils.EMPTY;
        private String titleColor = StringUtils.EMPTY;
        private String titleShow = StringUtils.EMPTY;

        public State build() {
            return new State(this);
        }

        public State.Builder fFamily(String fFamily) {
            this.fFamily = fFamily;
            return this;
        }

        public State.Builder fSize(String fSize) {
            this.fSize = fSize;
            return this;
        }

        public State.Builder fStyle(String fStyle) {
            this.fStyle = fStyle;
            return this;
        }

        public State.Builder fUnderline(String fUnderline) {
            this.fUnderline = fUnderline;
            return this;
        }

        public State.Builder image(String image) {
            this.image = image;
            return this;
        }

        public State.Builder title(String title) {
            this.title = title;
            return this;
        }

        public State.Builder titleAlignment(String titleAlignment) {
            this.titleAlignment = titleAlignment;
            return this;
        }

        public State.Builder titleColor(String titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public State.Builder titleShow(String titleShow) {
            this.titleShow = titleShow;
            return this;
        }
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