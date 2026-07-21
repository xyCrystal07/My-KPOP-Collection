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
}
