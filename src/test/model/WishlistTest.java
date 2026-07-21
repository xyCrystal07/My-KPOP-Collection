package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest {
    private Wishlist wishList;
    private CollectibleItem item;

    @BeforeEach
    void runBefore() {
        wishList = new Wishlist();
        item = new CollectibleItem("Ricky KCON Japan Venue Benefit PC", "Photocard", "Ricky", "You had me at HELLO", "KCON Japan Venue", "Mint", false, "");
    }

    @Test 
    void testConstructor() {
        assertEquals(0,wishList.size());
    }

    @Test
    void testAddItem() {
        assertEquals(0,wishList.size());
        wishList.addItem(item);
        assertEquals(1,wishList.size());
        assertTrue(wishList.contains(item));
    }

    @Test
    void testRemoveItem(){
        assertEquals(0,wishList.size());
        wishList.addItem(item);
        assertEquals(1,wishList.size());
        assertTrue(wishList.contains(item));

        wishList.removeItem(item);
        assertEquals(0,wishList.size());
        assertFalse(wishList.contains(item));
    }
}
