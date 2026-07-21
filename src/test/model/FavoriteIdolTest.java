package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FavoriteIdolTest {
    private FavoriteIdol idol;

    @BeforeEach
    void runBefore() {
        idol = new FavoriteIdol("Felix","Stray Kids", "Dance", "2000-09-15","MANIAC", 7);
    }

    @Test
    void testConstructor() {
        assertEquals("Felix", idol.getName());
        assertEquals("Stray Kids", idol.getGroup());
        assertEquals("Dance", idol.getPosition());
        assertEquals("2000-09-15", idol.getBirthday());
        assertEquals("MANIAC", idol.getFavoriteSong());
        assertEquals(7,idol.getBiasLevel());
    }

    @Test
    void setFavoriteSongTest() {
        idol.setFavoriteSong("LALALALA");
        assertEquals("LALALALA", idol.getFavoriteSong());
    }

    @Test
    void setBiasLevelTest() {
        idol.setBiasLevel(6);
        assertEquals(6,idol.getBiasLevel());
    }
}
