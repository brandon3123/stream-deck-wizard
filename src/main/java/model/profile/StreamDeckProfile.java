package model.profile;

public abstract class StreamDeckProfile {
    private String name;
    private String profileLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileLocation() {
        return profileLocation;
    }

    public void setProfileLocation(String profileLocation) {
        this.profileLocation = profileLocation;
    }

    @Override
    public String toString() {
        return "StreamDeckProfile{" +
                "name='" + name + '\'' +
                ", profileLocation='" + profileLocation + '\'' +
                '}';
    }
}