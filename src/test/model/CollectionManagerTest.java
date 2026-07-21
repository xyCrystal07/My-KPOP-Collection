package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionManagerTest {
     private CollectionManager manager;
     private FavoriteIdol idol;
     private CollectibleItem item;

     @BeforeEach
     void runBefore() {
        manager = new CollectionManager();
        idol = new FavoriteIdol("Ricky", "AND2BLE", "Visual", "2004-05-20", "SWEAT", 10);
        item = new CollectibleItem("Ricky KCON Japan Venue Benefit PC", "Photocard", "Ricky", "You had me at HELLO", "KCON Japan Venue", "Mint", false, "");
     }


     @Test
     void testConstructor() {
        assertEquals(0, manager.getFavoriteIdols().size());
        assertEquals(0, manager.getCollections().size());
        assertEquals(0, manager.getWishlist().size());
     }

     @Test 
     void testAddFavoriteIdol() {
        assertEquals(0, manager.getFavoriteIdols().size());
        manager.addFavoriteIdol(idol);
        assertEquals(1, manager.getFavoriteIdols().size());
        assertTrue(manager.getFavoriteIdols().contains(idol));
     }

     @Test
     void testRemoveFavoriteIdol() {
        manager.addFavoriteIdol(idol);
        assertEquals(1,manager.getFavoriteIdols().size());
        assertTrue(manager.getFavoriteIdols().contains(idol));

        manager.removeFavoriteIdol("Ricky");
        assertEquals(0,manager.getFavoriteIdols().size());
        assertFalse(manager.getFavoriteIdols().contains(idol));
     }

     @Test
     void testAddCollectibleItem() {
        assertEquals(0,manager.getCollections().size());
        manager.addCollectibleItem(item);
        assertEquals(1, manager.getCollections().size());
        assertTrue(manager.getCollections().contains(item));
     }

     @Test
     void testRemoveCollectibleItem() {
        manager.addCollectibleItem(item);
        assertEquals(1,manager.getCollections().size());
        assertTrue(manager.getCollections().contains(item));

        manager.removeCollectibleItem("Ricky KCON Japan Venue Benefit PC");
        assertEquals(0,manager.getCollections().size());
        assertFalse(manager.getCollections().contains(item));
     }

     @Test
     void testGetWishlist() {
        assertEquals(0,manager.getWishlist().size());
     }
}
