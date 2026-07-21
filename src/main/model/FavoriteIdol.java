package model;

//Represents a favourite K-pop idol
public class FavoriteIdol {
    private String name;
    private String group;
    private String position;
    private String birthday;
    private String favoriteSong;
    private int biasLevel;

    // EFFECTS: constructs a favorite idol with the given name, group, position, birthday, favorite song, and bias level.
    public FavoriteIdol(String name, String group, String position, String birthday, String favoriteSong, int biasLevel) {
        this.name = name;
        this.group = group;
        this.position = position;
        this.birthday = birthday;
        this.favoriteSong = favoriteSong;
        this.biasLevel = biasLevel;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }
    
    public String getFavoriteSong() {
        return favoriteSong;
    }

    public int getBiasLevel() {
        return biasLevel;
    }

    public void setFavoriteSong(String favoriteSong) {
        this.favoriteSong = favoriteSong;
    }

    public void setBiasLevel(int biasLevel) {
        this.biasLevel = biasLevel;
    }
}
