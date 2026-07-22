package persistence;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import model.CollectionManager;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CollectionManager manager = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyCollectionManager() {
        try {
            CollectionManager manager = new CollectionManager();

            JsonWriter writer = new JsonWriter("./data/testReaderEmptyCollectionManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyCollectionManager.json");
            manager = reader.read();

            assertEquals(0, manager.getFavoriteIdols().size());
            assertEquals(0, manager.getCollections().size());
            assertEquals(0, manager.getWishlist().getItems().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }


}
