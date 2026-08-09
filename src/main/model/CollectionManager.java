package model;

import java.util.ArrayList;
import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a manager of K-pop collection
public class CollectionManager implements Writable {
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
        EventLog.getInstance().logEvent(new Event("Favorite idol added: " + idol.getName()));
    }

    // MODIFIES: this
    // EFFECTS: removes the favorite idol with the given name if it exists
    public boolean removeFavoriteIdol(String name) {
        FavoriteIdol idol = findFavoriteIdol(name);

        if (idol != null) {
            idols.remove(idol);
            EventLog.getInstance().logEvent(new Event("Favorite idol removed: " + idol.getName()));
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
        EventLog.getInstance().logEvent(new Event("Collectible item added: " + item.getItemName()));
    }

    // MODIFIES: this
    // EFFECTS: removes the collectible item with the given item name if it exists;
    //          returns true if removed, false otherwise
    public boolean removeCollectibleItem(String itemName) {
        CollectibleItem item = findCollectibleItem(itemName);

        if (item != null) {
            collections.remove(item);
            EventLog.getInstance().logEvent(new Event("Collectible item removed: " + item.getItemName()));
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

    @Override
    public JSONObject toJson() {
        // TODO Auto-generated method stub
        JSONObject json = new JSONObject();
        json.put("idols", idolsToJson());
        json.put("collections", collectionsToJson());
        json.put("wishList", wishList.toJson());
        return json;
    }

    // EFFECTS: returns favorite idols in this collection manager as a JSON array
    private JSONArray idolsToJson() {
        JSONArray jsonArray = new JSONArray();
        
        for (FavoriteIdol idol: idols) {
            jsonArray.put(idol.toJson());
        }
        return jsonArray;
    }
    
    // EFFECTS: returns collectible items in this collection manager as a JSON array
    private JSONArray collectionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CollectibleItem item : collections) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }
    
}