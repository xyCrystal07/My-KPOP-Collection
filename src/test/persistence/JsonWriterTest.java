package persistence;

import model.CollectionManager;
import model.CollectibleItem;
import model.FavoriteIdol;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            CollectionManager manager = new CollectionManager();
            JsonWriter writer = new JsonWriter("./data/\0illegal:file.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyCollectionManager() {
        try {
            CollectionManager manager = new CollectionManager();

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCollectionManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCollectionManager.json");
            manager = reader.read();

            assertEquals(0, manager.getFavoriteIdols().size());
            assertEquals(0, manager.getCollections().size());
            assertEquals(0, manager.getWishlist().getItems().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testWriterGeneralCollectionManager() {
        try {
            CollectionManager manager = new CollectionManager();

            FavoriteIdol idol = new FavoriteIdol("Karina",  "aespa", "Leader", "2000-04-11", "Supernova", 5);
            CollectibleItem collectionItem = new CollectibleItem("Drama Album", "Album", "Karina", "Drama", "Photobook",  "New",  true,  "First album");
            CollectibleItem wishlistItem = new CollectibleItem("Armageddon Photocard",  "Photocard",  "Karina", "Armageddon", "A Version", "Mint", false,  "Want to buy");

            manager.addFavoriteIdol(idol);
            manager.addCollectibleItem(collectionItem);
            manager.getWishlist().addItem(wishlistItem);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollectionManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCollectionManager.json");
            manager = reader.read();

            assertEquals(1, manager.getFavoriteIdols().size());
            assertEquals(1, manager.getCollections().size());
            assertEquals(1, manager.getWishlist().getItems().size());

            checkFavoriteIdol("Karina", "aespa", "Leader", "2000-04-11", "Supernova", 5, manager.getFavoriteIdols().get(0));
            checkCollectibleItem("Drama Album", "Album", "Karina", "Drama", "Photobook", "New", true,"First album", manager.getCollections().get(0));
            checkCollectibleItem("Armageddon Photocard", "Photocard", "Karina", "Armageddon", "A Version", "Mint", false, "Want to buy", manager.getWishlist().getItems().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    void testReaderGeneralCollectionManager() {
        try {
            CollectionManager manager = new CollectionManager();

            FavoriteIdol idol = new FavoriteIdol("Karina", "aespa", "Leader", "2000-04-11", "Supernova", 5);
            CollectibleItem collectionItem = new CollectibleItem("Drama Album", "Album", "Karina", "Drama", "Photobook", "New", true, "First album");
            CollectibleItem wishlistItem = new CollectibleItem("Armageddon Photocard", "Photocard", "Karina", "Armageddon", "A Version", "Mint", false, "Want to buy");

            manager.addFavoriteIdol(idol);
            manager.addCollectibleItem(collectionItem);
            manager.getWishlist().addItem(wishlistItem);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralCollectionManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralCollectionManager.json");
            manager = reader.read();

            assertEquals(1, manager.getFavoriteIdols().size());
            assertEquals(1, manager.getCollections().size());
            assertEquals(1, manager.getWishlist().getItems().size());

            checkFavoriteIdol("Karina", "aespa", "Leader", "2000-04-11", "Supernova", 5, manager.getFavoriteIdols().get(0));

            checkCollectibleItem("Drama Album", "Album", "Karina", "Drama", "Photobook", "New", true, "First album", manager.getCollections().get(0));

            checkCollectibleItem("Armageddon Photocard", "Photocard", "Karina", "Armageddon", "A Version", "Mint", false, "Want to buy", manager.getWishlist().getItems().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }
}
