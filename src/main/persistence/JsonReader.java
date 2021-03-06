package persistence;

import model.Bank;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Bank from JSON data stored in file
// code gotten from JsonSerializationDemo
public class JsonReader {
    private final String source;

    // Effects: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Bank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Bank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Bank from JSON object and returns it
    private Bank parseBank(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Bank bank = new Bank(name);
        changeBalance(bank, jsonObject);
        return bank;
    }

    // MODIFIES: bank
    // EFFECTS: parses balance from JSON object and adds it to Bank
    private void changeBalance(Bank bank, JSONObject jsonObject) {
        Number balance = jsonObject.getNumber("balance");
        bank.changeBalance((Integer) balance);
    }

}
