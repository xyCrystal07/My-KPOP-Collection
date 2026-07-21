package model;

public class CollectibleItem {
    private String itemName;
    private String itemType;
    private String ownerIdol;
    private String album;
    private String version;
    private String condition;
    private boolean owned;
    private String notes;

    // 
    public CollectibleItem(String itemName, String itemType, String ownerIdol, String album, String version, String condition, boolean owned, String notes) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.ownerIdol = ownerIdol;
        this.album = album;
        this.version = version;
        this.condition = condition;
        this.owned = owned;
        this.notes = notes;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getOwnerIdol() {
        return ownerIdol;
    }

    public String getAlbum() {
        return album;
    }

    public String getVersion() {
        return version;
    }

    public String getCondition() {
        return condition;
    }

    public boolean isOwned() {
        return owned;
    }

    public String getNotes() {
        return notes;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
