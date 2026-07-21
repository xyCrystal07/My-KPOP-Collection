package model;

import java.util.ArrayList;

// Represents a wishlist that stores collectible items
public class Wishlist {
    private ArrayList<CollectibleItem> items;

    // EFFECTS: constructs an empty wishlist
    public Wishlist() {
        items = new ArrayList<>();
    }

    public ArrayList<CollectibleItem> getItems() {
        return items;
    }

    // MODIFIES: this
    // EFFECTS: adds the given item into the wishlist
    public void addItem(CollectibleItem item) {
        items.add(item);
    }

    // REQUIRES: item is in the wishlist
    // MODIFIDES: this
    // EFFECTS: removes the given item from the wishlist
    public void removeItem(CollectibleItem item) {
        items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public boolean contains(CollectibleItem item) {
        return items.contains(item);
    }
}
