package persistence;

import model.CollectionManager;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Reoresents a writer that writes JSON representation of collection manager
public class JsonWriter {
    private static final int TAB  = 4;
    private PrintWriter writer;
    private String destination;

    
    // EFFECTS: constructs writer to writer to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing;
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of collection manager to file
    public void write(CollectionManager manager) {
        JSONObject json = manager.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFEIS: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

    
}
