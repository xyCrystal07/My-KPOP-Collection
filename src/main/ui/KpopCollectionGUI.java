package ui;

import model.CollectionManager;
import model.FavoriteIdol;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public KpopCollectionGUI() {
        super("My K-pop Collection");
        manager = new CollectionManager();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeFrame();
    }

    private void initializeFrame() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        initializeButtons();

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setVisible(true);
    }

    private void initializeButtons() {
        JPanel buttonPanel = new JPanel();

        addIdolButton = new JButton("Add Idol");
        addIdolButton.addActionListener(this);
        showIdolsButton = new JButton("Show Idols");
        showIdolsButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);

        buttonPanel.add(addIdolButton);
        buttonPanel.add(showIdolsButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIdolButton) {
            addFavoriteIdol();
        } else if (e.getSource() == showIdolsButton) {
            showFavoriteIdols();
        }
    }

    private void showFavoriteIdols() {
        displayArea.setText("");
        if (manager.getFavoriteIdols().isEmpty()) {
            displayArea.setText("No favorite idols.");
            return;
        }
        for (FavoriteIdol idol : manager.getFavoriteIdols()) {
            displayArea.append(idol.getName() + "\n");
        }
    }

    private void addFavoriteIdol() {
        String name = JOptionPane.showInputDialog(this, "Enter idol name:");
        if (name == null || name.isEmpty()) {
            return;
        }
        String group = JOptionPane.showInputDialog(this, "Enter group:");
        FavoriteIdol idol = new FavoriteIdol(name, group, "", "", "", 0);
        manager.addFavoriteIdol(idol);
        JOptionPane.showMessageDialog(this, "Favorite idol added.");
    }
}
