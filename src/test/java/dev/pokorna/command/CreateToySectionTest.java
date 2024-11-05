package dev.pokorna.command;

import junit.framework.TestCase;

import dev.pokorna.model.ToyRoom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CreateToySectionTest extends TestCase {
    private ToyRoom toyRoom;
    private CreateToySection createToySection;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        toyRoom = new ToyRoom();
        createToySection = new CreateToySection(toyRoom);
        System.setOut(new PrintStream(outputStream));
    }

    public void testExecute() {
        String sectionName = "Тестова секція";
        System.setIn(new ByteArrayInputStream((sectionName + "\n").getBytes()));
        createToySection.execute();
        String output = outputStream.toString().trim();
        boolean sectionExists = toyRoom.getSections().stream()
                .anyMatch(section -> section.getSectionName().equals(sectionName));
        assertTrue("Expected the ToyRoom to contain the new section.", sectionExists);
        assertTrue("Expected output message to confirm section creation.",
                output.contains("Секція '" + sectionName + "' успішно створена!"));
    }

    public void testName() {
        assertEquals("Створити секцію у ляльковій кімнаті", createToySection.name());
    }

    public void testGetDescription() {
        String expectedDescription = "Функція дозволяє створити нову секцію у ляльковій кімнаті." +
                "Щоб додати нову секцію необхідно ввести назву секції.";
        assertEquals(expectedDescription, createToySection.getDescription());
    }
}