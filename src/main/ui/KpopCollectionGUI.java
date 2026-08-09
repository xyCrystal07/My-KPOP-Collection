package ui;

import model.CollectionManager;
import model.FavoriteIdol;
import model.CollectibleItem;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KpopCollectionGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/kpopCollection.json";
    private CollectionManager manager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextArea displayArea;
    private JButton addIdolButton;
    private JButton showIdolsButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addItemButton;
    private JButton showCollectionButton;
    private JLabel imageLabel;

    public KpopCollectionGUI() {
        super("My K-pop Collection");
        manager = new CollectionManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes the main GUI window and its components
    private void initializeFrame() {
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(230, 240, 255));
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        displayArea.setText(
                "Welcome to My K-pop Collection!\n\n"
            + "Manage your favorite idols and\n"
            + "collectible items here.\n\n"
            + "• Add your favorite idols\n"
            + "• Add collectible items\n"
            + "• Save and load your collection\n\n"
            + "Click a button above to get started."
        );
        initializeButtons();

        ImageIcon originalImage = new ImageIcon("images/ricky.jpg");
        Image resizedImage = originalImage.getImage().getScaledInstance(220, 300, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(resizedImage));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Collection Information"));
        add(scrollPane, BorderLayout.CENTER);
        add(imageLabel, BorderLayout.EAST);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons and adds them to the window
    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();

        addIdolButton = new JButton("Add Idol");
        addIdolButton.addActionListener(this);
        showIdolsButton = new JButton("Show Idols");
        showIdolsButton.addActionListener(this);
        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(this);
        showCollectionButton = new JButton("Show Collection");
        showCollectionButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);

        buttonPanel.add(addIdolButton);
        buttonPanel.add(showIdolsButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(showCollectionButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: processes button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIdolButton) {
            addFavoriteIdol();
        } else if (e.getSource() == showIdolsButton) {
            showFavoriteIdols();
        } else if (e.getSource() == addItemButton) {
            addCollectibleItem();
        } else if (e.getSource() == showCollectionButton) {
            showCollection();
        } else if (e.getSource() == saveButton) {
            saveCollection();
        }  else if (e.getSource() == loadButton) {
            loadCollection();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter an idol and adds it to the collection
    private void addFavoriteIdol() {
        String name = JOptionPane.showInputDialog(this, "Enter idol name:");
        if (name == null || name.isEmpty()) {
            return;
        }
        String group = JOptionPane.showInputDialog(this, "Enter group:");
        String position = JOptionPane.showInputDialog(this, "Enter position:");
        String birthday = JOptionPane.showInputDialog(this, "Enter birthday:");
        String song = JOptionPane.showInputDialog(this, "Enter favorite song:");
        String level = JOptionPane.showInputDialog(this, "Enter bias level:");
        int biasLevel = Integer.parseInt(level);
        FavoriteIdol idol = new FavoriteIdol(name, group, position, birthday, song, biasLevel);
        manager.addFavoriteIdol(idol);
        JOptionPane.showMessageDialog(this, "Favorite idol added.","Success", JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: displays all favorite idols in the text area
    private void showFavoriteIdols() {
        displayArea.setText("");
        if (manager.getFavoriteIdols().isEmpty()) {
            displayArea.setText("No favorite idols.");
            return;
        }
        for (FavoriteIdol idol : manager.getFavoriteIdols()) {
            displayArea.append(idol.getName() + "\n");
            displayArea.append("Group: " + idol.getGroup() + "\n");
            displayArea.append("Position: " + idol.getPosition() + "\n");
            displayArea.append("Birthday: " + idol.getBirthday() + "\n");
            displayArea.append("Favorite Song: " + idol.getFavoriteSong() + "\n");
            displayArea.append("Bias Level: " + idol.getBiasLevel() + "\n");
            displayArea.append("----------------------------------\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter an item and adds it to the collection
    private void addCollectibleItem() {
        String itemName = JOptionPane.showInputDialog(this, "Enter item name:");
        if (itemName == null || itemName.isEmpty()) {
            return;
        }
        String itemType = JOptionPane.showInputDialog(this, "Enter item type:");
        String owner = JOptionPane.showInputDialog(this, "Enter owner idol:");
        String album = JOptionPane.showInputDialog(this, "Enter album:");
        String version = JOptionPane.showInputDialog(this, "Enter version:");
        String condition = JOptionPane.showInputDialog(this, "Enter condition:");
        String ownedInput = JOptionPane.showInputDialog(this, "Do you own this item? (true/false)");
        boolean owned = Boolean.parseBoolean(ownedInput);
        String notes = JOptionPane.showInputDialog(this, "Enter notes:");
        CollectibleItem item = new CollectibleItem(itemName, itemType, owner, album, version, condition, owned, notes);
        manager.addCollectibleItem(item);
        JOptionPane.showMessageDialog(this, "Collectible item added.", "Success", JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: displays all collectible items in the text area
    private void showCollection() {
        displayArea.setText("");
        if (manager.getCollections().isEmpty()) {
            displayArea.setText("No collectible items.");
            return;
        }
        for (CollectibleItem item : manager.getCollections()) {
            displayArea.append(item.getItemName() + "\n");
            displayArea.append("Item Type: " + item.getItemType() + "\n");
            displayArea.append("Owner Idol: " + item.getOwnerIdol() + "\n");
            displayArea.append("Album: " + item.getAlbum() + "\n");
            displayArea.append("Version: " + item.getVersion() + "\n");
            displayArea.append("Condition: " + item.getCondition() + "\n");
            displayArea.append("Owned: " + item.isOwned() + "\n");
            displayArea.append("Notes: " + item.getNotes() + "\n");
            displayArea.append("----------------------------------\n");
        }
    }

    // EFFECTS: saves the current collection to file
    private void saveCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(manager);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Collection saved.", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the collection from file
    private void loadCollection() {
        try {
            manager = jsonReader.read();
            displayArea.setText("Collection loaded successfully.\n\n" + "Click \"Show Idols\" or \"Show Collection\" "  + "to view your data.");
            JOptionPane.showMessageDialog(this, "Collection loaded.",  "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: prints all logged events to the console
    private void printEventLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }
}
