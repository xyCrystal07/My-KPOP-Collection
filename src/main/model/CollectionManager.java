package model;

import java.util.ArrayList;

// Represents a manager of K-pop collection
public class CollectionManager {
    private ArrayList<FavoriteIdol> idols;
    private ArrayList<CollectibleItem> collections;
    private Wishlist wishList;

    // EFFECTS: constructs an empty collection manager with no favorite idols,
    // no collectible items, and an empty wishlist
    public CollectionManager() {
        idols = new ArrayList<>();
        collections = new ArrayList<>();
        wishList = new Wishlist();
    }

    // MODIFIES: this
    // EFFECTS: adds the given favorite idol to the collection manager
    public void addFavoriteIdol(FavoriteIdol idol) {
        idols.add(idol);
    }

    // MODIFIES: this
    // EFFECTS: removes the favorite idol with the given name if it exists
    public boolean removeFavoriteIdol(String name) {
        FavoriteIdol idol = findFavoriteIdol(name);

        if (idol != null) {
            idols.remove(idol);
            return true;
        }

        return false;
    }

    // EFFECTS: returns the favorite idol with the given name;
    // returns null if no such idol exists
    public FavoriteIdol findFavoriteIdol(String name) {
        for (FavoriteIdol idol : idols) {
            if (idol.getName().equalsIgnoreCase(name)) {
                return idol;
            }
        }
        return null;
    }

    // EFFECTS: returns the list of favorite idols
    public ArrayList<FavoriteIdol> getFavoriteIdols() {
        return idols;
    }

    // MODIFIES: this
    // EFFECTS: adds the given collectible item to the collection manager
    public void addCollectibleItem(CollectibleItem item) {
        collections.add(item);
    }

    // MODIFIES: this
    // EFFECTS: removes the collectible item with the given item name if it exists;
    //          returns true if removed, false otherwise
    public boolean removeCollectibleItem(String itemName) {
        CollectibleItem item = findCollectibleItem(itemName);

        if (item != null) {
            collections.remove(item);
            return true;
        }

        return false;
    }

    // EFFECTS: returns the collectible item with the given item name;
    // returns null if no such collectible item exists
    public CollectibleItem findCollectibleItem(String itemName) {
        for (CollectibleItem item : collections) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // EFFECTS: returns the list of collectible items
    public ArrayList<CollectibleItem> getCollections() {
        return collections;
    }

    // EFFECTS: returns the wishlist
    public Wishlist getWishlist() {
        return wishList;
    }
}