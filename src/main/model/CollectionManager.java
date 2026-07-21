package model;

import java.util.ArrayList;

// Represents a manager of K-pop collection
public class CollectionManager {
    private ArrayList<FavoriteIdol> idols;
    private ArrayList<CollectibleItem> collections;
    private Wishlist wishList;

    // EFFECTS: constructs an empty collection manager with no favorite idols, no collectible items, and an empty wishlist
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
    // REQUIRES: idol is in this collection manager
    // EFFECTS: remove the given favorite idol from the collection manager
    public void removeFavoriteIdol(FavoriteIdol idol) {
        idols.remove(idol);
    }

    // EFFECTS: returns the list of favorite idols;
    public ArrayList<FavoriteIdol> getFavoriteIdols() {
        return idols;
    }

    // MODIFIES: this
    // EFFECTS: adds the given collectible item to the collection manager
    public void addCollectibleItem(CollectibleItem item) {
        collections.add(item);
    }

    // MODIFIES: this
    // REQUIRES: item is in this collection manager
    // EFFECTS: removes the given collectible item from the collection manager
    public void removeCollectibleItem(CollectibleItem item) {
        collections.remove(item);
    }

    // EFFECTS: returns the list of collections
    public ArrayList<CollectibleItem> getCollections() {
        return collections;
    }

    // EFFECTS: returns the wishlist
    public Wishlist getWishlist() {
        return wishList;
    }
}
