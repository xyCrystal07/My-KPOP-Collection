package persistence;

import model.CollectibleItem;
import model.FavoriteIdol;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {
    protected void checkFavoriteIdol(String name, String group, String position, String birthday, String favoriteSong, int biasLevel, FavoriteIdol idol) {
        assertEquals(name, idol.getName());
        assertEquals(group, idol.getGroup());
        assertEquals(position, idol.getPosition());
        assertEquals(birthday, idol.getBirthday());
        assertEquals(favoriteSong, idol.getFavoriteSong());
        assertEquals(biasLevel, idol.getBiasLevel());
    }

    protected void checkCollectibleItem(String itemName, String itemType, String ownerIdol, String album, String version, String condition, boolean owned, String notes, CollectibleItem item) {
        assertEquals(itemName, item.getItemName());
        assertEquals(itemType, item.getItemType());
        assertEquals(ownerIdol, item.getOwnerIdol());
        assertEquals(album, item.getAlbum());
        assertEquals(version, item.getVersion());
        assertEquals(condition, item.getCondition());
        assertEquals(owned, item.isOwned());
        assertEquals(notes, item.getNotes());
    }

    
}
