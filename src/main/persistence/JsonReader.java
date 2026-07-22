package persistence;

import model.CollectionManager;
import model.CollectibleItem;
import model.FavoriteIdol;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads CollectionManager from JSON data stored in file
public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads collection manager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CollectionManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        CollectionManager manager = new CollectionManager();
        parseCollectionManager(manager,jsonObject);
        return manager;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(builder::append);
        }
        return builder.toString();
    }

    // MODIFIES: manager
    // EFFECTS: parses collection manager from JSON object
    private void parseCollectionManager(CollectionManager manager, JSONObject jsonObject) {
        addFavoriteIdols(manager,jsonObject);
        addCollectibleItems(manager,jsonObject);
        addWishlist(manager, jsonObject);
    }

    // MODIFIES; manager
    // EFFECTS: parse favorie idols from JSON object and adds them to collection manager
    private void addFavoriteIdols(CollectionManager manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("idols");
        for(Object json: jsonArray) {
            JSONObject nextIdol = (JSONObject) json;
            String name = nextIdol.getString("name");
            String group = nextIdol.getString("group");
            String position = nextIdol.getString("position");
            String birthday = nextIdol.getString("birthday");
            String favoriteSong = nextIdol.getString("favoriteSong");
            int biasLevel = nextIdol.getInt("biasLevel");

            FavoriteIdol idol = new FavoriteIdol(name, group,position, birthday, favoriteSong, biasLevel);
            manager.addFavoriteIdol(idol);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses collectible items from JSON object and adds them to collection manager
    private void addCollectibleItems(CollectionManager manager, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("collections");

        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;

            String itemName = nextItem.getString("itemName");
            String itemType = nextItem.getString("itemType");
            String ownerIdol = nextItem.getString("ownerIdol");
            String album = nextItem.getString("album");
            String version = nextItem.getString("version");
            String condition = nextItem.getString("condition");
            boolean owned = nextItem.getBoolean("owned");
            String notes = nextItem.getString("notes");

            CollectibleItem item = new CollectibleItem(itemName, itemType,
                    ownerIdol, album, version, condition, owned, notes);

            manager.addCollectibleItem(item);
        }
    }

    // MODIFIES: manager
    // EFFECTS: parses wishlist from JSON object and adds its items to the wishlist
    private void addWishlist(CollectionManager manager, JSONObject jsonObject) {
        JSONObject wishlistObject = jsonObject.getJSONObject("wishList");
        JSONArray jsonArray = wishlistObject.getJSONArray("items");

        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;

            String itemName = nextItem.getString("itemName");
            String itemType = nextItem.getString("itemType");
            String ownerIdol = nextItem.getString("ownerIdol");
            String album = nextItem.getString("album");
            String version = nextItem.getString("version");
            String condition = nextItem.getString("condition");
            boolean owned = nextItem.getBoolean("owned");
            String notes = nextItem.getString("notes");

            CollectibleItem item = new CollectibleItem(itemName, itemType,
                    ownerIdol, album, version, condition, owned, notes);

            manager.getWishlist().addItem(item);
        }
    }
}
