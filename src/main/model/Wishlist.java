package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a wishlist that stores collectible items
public class Wishlist implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in this wishlist as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();
        
        for (CollectibleItem item : items) {
            jsonArray.put(item.toJson());
        }

        return jsonArray;
    }
}
