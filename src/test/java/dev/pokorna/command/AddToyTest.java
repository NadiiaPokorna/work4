package dev.pokorna.command;

import dev.pokorna.exception.CriticalError;
import dev.pokorna.model.Toy;
import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.ToySection;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AddToyTest extends TestCase {

    private ToyRoom toyRoom;
    private AddToy addToy;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ByteArrayInputStream inContent;

    public AddToyTest() {
        String simulatedInput = "Test Toy\nCar\nMedium\n100.0\n1\n";
        inContent = new ByteArrayInputStream(simulatedInput.getBytes());
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        toyRoom = new ToyRoom();
        ToySection section = new ToySection("Test Section");
        toyRoom.addSection(section);
        addToy = new AddToy(toyRoom);
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    public void testExecute() throws CriticalError {
        String simulatedInput = "Test Toy\nCar\nMedium\n100.0\n1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        addToy.execute();

        assertEquals(1, toyRoom.getSections().get(0).getToys().size());
        Toy addedToy = toyRoom.getSections().get(0).getToys().get(0);
        assertEquals("Test Toy", addedToy.getName());
        assertEquals("Car", addedToy.getType());
        assertEquals("Medium", addedToy.getSize());
        assertEquals(100.0, addedToy.getPrice());
    }

    public void testName() {
        assertEquals("Додати нову іграшку", addToy.name());
    }

    public void testGetDescription() {
        assertEquals("Функція дозволяє додати нові іграшки. " +
                "Щоб додати нову необхідно ввести назву, тип, розмір і секцію в яку бажаєте її додати.", addToy.getDescription());
    }
}
