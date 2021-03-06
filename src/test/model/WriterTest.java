package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {

    @Test
    // test based off of test from JsonSerializationDemo
    public void testWriterInvalidFile() {
        try {
            Bank bank = new Bank("testBank");
            JsonWriter writer = new JsonWriter("./data/\0youWillNeverFindMe.json");
            writer.open();
            assertEquals(1000, bank.getBalance());
            fail();
        } catch (IOException e) {
            // Success
        }
    }

    @Test
    public void testWriterBaseBank() {
        try {
            Bank bank = new Bank("baseBankWriter");
            JsonWriter writer = new JsonWriter("./data/testBaseBankWriter.json");
            writer.open();
            writer.write(bank);
            writer.close();
            JsonReader reader = new JsonReader("./data/testBaseBankWriter.json");
            bank = reader.read();
            assertEquals("baseBankWriter", bank.getName());
            assertEquals(1000, bank.getBalance());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testWriterChangeBankBal() {
        try {
            Bank bank = new Bank("changingBank");
            assertEquals(1000, bank.getBalance());
            bank.changeBalance(100);
            JsonWriter writer = new JsonWriter("./data/testChangingBankWriter.json");
            writer.open();
            writer.write(bank);
            writer.close();
            JsonReader reader = new JsonReader("./data/testChangingBankWriter.json");
            bank = reader.read();
            assertEquals("changingBank", bank.getName());
            assertEquals(100, bank.getBalance());
        } catch (IOException e) {
            fail();
        }
    }
}
