package ui;

import java.util.Scanner;
import model.FavoriteIdol;
import model.CollectibleItem;
import model.CollectionManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CollectionApp {
    private Scanner input;
    private CollectionManager manager;
    private boolean keepGoing;
    private static final String JSON_STORE = "./data/kpopCollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public CollectionApp() {
        manager = new CollectionManager();
        input = new Scanner(System.in);
        keepGoing = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runCollection();
    }

    // MODIFIES: this
    // EFFECTS: processes user input until the user quits
    public void runCollection() {
        while (keepGoing) {
            displayMenu();
            String command = input.nextLine().toLowerCase();
            processCommand(command);
        }
        
        System.out.println("Goodbye!");
    }

    // EFFECTS: displays the menu
    private void displayMenu() {
        System.out.println();
        System.out.println("========== My K-pop Collection ==========");
        System.out.println("a - Add favorite idol");
        System.out.println("b - Remove favorite idol");
        System.out.println("c - Add collectible item");
        System.out.println("d - Remove collectible item");
        System.out.println("e - View favorite idols");
        System.out.println("f - View collectible items");
        System.out.println("g - View wishlist");
        System.out.println("s - Save collection");
        System.out.println("l - Load collection");  
        System.out.println("q - Quit");
        System.out.print("Choose: ");
    }

    // MODIFIES: this
    // EFFECTS: processes user commands
    public void processCommand(String command) {
        switch (command) {
            case "a":
                addFavoriteIdol();
                break;
            case "b":
                removeFavoriteIdol();
                break;
            case "c":
                addCollectibleItem();
                break;
            case "d":
                removeCollectibleItem();
                break;
            case "e":
                viewFavoriteIdols();
                break;
            case "f":
                viewCollection();
                break;
            case "g":
                viewWishlist();
                break;
            case "q":
                keepGoing = false;
                break;
            case "s":
                saveCollection();
            break;
            case "l":
                loadCollection();
            break;
            default:
                System.out.println("Invalid command.");

        }
    }

    // EFFECTS: saves the collection manager to file
    private void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            System.out.println("Saved collection to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads collection manager from file
    private void loadCollection() {
        try {
            manager = jsonReader.read();
            System.out.println("Loaded collection from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
    // public void processCommand(String command) {
    //     if (processAddRemove(command)) {
    //         return;
    //     }

    //     if (processView(command)) {
    //         return;
    //     }

    //     processQuit(command);
    // }

    // private boolean processAddRemove(String command) {
    //     switch (command) {
    //         case "a":
    //             addFavoriteIdol();
    //             return true;
    //         case "b":
    //             removeFavoriteIdol();
    //             return true;
    //         case "c":
    //             addCollectibleItem();
    //             return true;
    //         case "d":
    //             removeCollectibleItem();
    //             return true;
    //         default:
    //             return false;
    //     }
    // }

    // private boolean processView(String command) {
    //     switch (command) {
    //         case "e":
    //             viewFavoriteIdols();
    //             return true;
    //         case "f":
    //             viewCollection();
    //             return true;
    //         case "g":
    //             viewWishlist();
    //             return true;
    //         default:
    //             return false;
    //     }
    // }

    // private void processQuit(String command) {
    //     if (command.equals("q")) {
    //         keepGoing = false;
    //     } else {
    //         System.out.println("Invalid command.");
    //     }
    // }
    
    // MODIFIES: this
    // EFFECTS: adds a favorite idol
    private void addFavoriteIdol() {
        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Group: ");
        String group = input.nextLine();

        System.out.print("Position: ");
        String position = input.nextLine();

        System.out.print("Birthday: ");
        String birthday = input.nextLine();

        System.out.print("Favorite song: ");
        String song = input.nextLine();

        System.out.print("Bias level: ");
        int level = Integer.parseInt(input.nextLine());

        FavoriteIdol idol = new FavoriteIdol(name, group, position, birthday, song, level);
        manager.addFavoriteIdol(idol);
        System.out.println("Favorite idol added.");
    }

    // MODIFIES: this
    // EFFECTS: removes a favorite idol
    private void removeFavoriteIdol() {
        System.out.print("Enter idol name: ");
        String name = input.nextLine();
        if (manager.removeFavoriteIdol(name)) {
            System.out.println("Favorite idol removed.");
        } else {
            System.out.println("Idol not found.");
        }

    }

    // MODIFIES: this
    // EFFECTS: adds a collectible item
    private void addCollectibleItem() {

        System.out.print("Item name: ");
        String itemName = input.nextLine();

        System.out.print("Item type: ");
        String itemType = input.nextLine();

        System.out.print("Owner idol: ");
        String owner = input.nextLine();

        System.out.print("Album: ");
        String album = input.nextLine();

        System.out.print("Version: ");
        String version = input.nextLine();

        System.out.print("Condition: ");
        String condition = input.nextLine();

        System.out.print("Do you own this item? (y/n): ");
        String answer = input.nextLine();

        boolean owned = answer.equalsIgnoreCase("y");

        CollectibleItem item = new CollectibleItem(itemName, itemType, owner, album, version, condition, owned, "");
        if (owned) {
            manager.addCollectibleItem(item);
            System.out.println("Collectible item added.");
        } else {
            manager.getWishlist().addItem(item);
            System.out.println("Item added to wishlist.");
        }       
    }

    // MODIFIES: this
    // EFFECTS: removes a collectible item
    private void removeCollectibleItem() {
        System.out.print("Enter item name: ");
        String itemName = input.nextLine();

        if (manager.removeCollectibleItem(itemName)) {
            System.out.println("Collectible item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // EFFECTS: displays all favorite idols
    private void viewFavoriteIdols() {

        if (manager.getFavoriteIdols().isEmpty()) {
            System.out.println("No favorite idols.");
            return;
        }

        for (FavoriteIdol idol : manager.getFavoriteIdols()) {
            System.out.println("----------------------------");
            System.out.println("Name: " + idol.getName());
            System.out.println("Group: " + idol.getGroup());
            System.out.println("Position: " + idol.getPosition());
            System.out.println("Birthday: " + idol.getBirthday());
            System.out.println("Favorite Song: " + idol.getFavoriteSong());
            System.out.println("Bias Level: " + idol.getBiasLevel());
        }
    }

    // EFFECTS: displays all collectible items
    private void viewCollection() {
        if (manager.getCollections().isEmpty()) {
            System.out.println("No collectible items.");
            return;
        }

        for (CollectibleItem item : manager.getCollections()) {
            System.out.println("----------------------------");
            System.out.println("Item: " + item.getItemName());
            System.out.println("Type: " + item.getItemType());
            System.out.println("Owner: " + item.getOwnerIdol());
            System.out.println("Album: " + item.getAlbum());
            System.out.println("Version: " + item.getVersion());
            System.out.println("Condition: " + item.getCondition());
        }
    }

    // EFFECTS: displays the wishlist
    private void viewWishlist() {

        if (manager.getWishlist().size() == 0) {
            System.out.println("Wishlist is empty.");
            return;
        }

        for (CollectibleItem item : manager.getWishlist().getItems()) {
            System.out.println(item.getItemName());
        }
    }
}
