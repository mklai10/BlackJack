package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesNotexist.json");
        try {
            Bank bank = reader.read();
            fail();
        } catch (IOException e) {
            // success
        }
    }

    @Test
    public void testReaderBaseBank() {
        JsonReader reader = new JsonReader("./data/testBaseBankReader.json");
        try {
            Bank bank = reader.read();
            assertEquals("baseBank", bank.getName());
            assertEquals(1000, bank.getBalance());
        } catch (IOException e) {
            fail();
        }
    }
}
