package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollectibleItemTest {
    private CollectibleItem item;

    @BeforeEach
    void runBefore() {
        item = new CollectibleItem("Ricky Model Student JP Benefit PC", "Photocard", "Ricky", "Melting Point", "Model Student JP", "Mint", true, "Bought in Xianyu");
    }

    @Test 
    void testConstructor() {
        assertEquals("Ricky Model Student JP Benefit PC", item.getItemName());
        assertEquals("Photocard", item.getItemType());
        assertEquals("Ricky", item.getOwnerIdol());
        assertEquals("Melting Point", item.getAlbum());
        assertEquals("Model Student JP", item.getVersion());
        assertEquals("Mint", item.getCondition());
        assertTrue(item.isOwned());
        assertEquals("Bought in Xianyu", item.getNotes());
    }
    
    @Test
    void testSetCondition() {
        item.setCondition("Near Mint");
        assertEquals("Near Mint", item.getCondition());
    }

    @Test
    void testSetNotes() {
        item.setNotes("Bought in Seoul");
        assertEquals("Bought in Seoul", item.getNotes());
    }

    @Test
    void testSetOwned() {
        item.setOwned(false);
        assertEquals(false,item.isOwned());
    }
}
