package dev.pokorna.command;

import junit.framework.TestCase;


import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.Toy;
import dev.pokorna.model.ToySection;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortToysTest {

    private ToyRoom toyRoom;
    private SortToys sortToys;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        toyRoom = new ToyRoom();
        sortToys = new SortToys(toyRoom);
        System.setOut(new PrintStream(outputStream));
        ToySection section1 = new ToySection("Секція 1");
        section1.addToy(new Toy("Іграшка А",  "A", "Маленька", 50));
        section1.addToy(new Toy("Іграшка Б", "B", "Велика", 30));
        section1.addToy(new Toy("Іграшка В", "C", "Середня", 20));
        toyRoom.addSection(section1);
        ToySection section2 = new ToySection("Секція 2");
        section2.addToy(new Toy("Іграшка Г", "D", "Середня", 40));
        section2.addToy(new Toy("Іграшка Д", "E", "Велика", 40));
        toyRoom.addSection(section2);
    }

    @Test
    public void testExecuteWithUnknownSortParameter() {
        System.setIn(new ByteArrayInputStream("unknown\n".getBytes()));
        sortToys.execute();
        String output = outputStream.toString().trim();
        assertTrue("Expected output for unknown sort parameter.", output.contains("Невідомий параметр сортування: unknown"));
    }

    @Test
    public void testName() {
        assertEquals("Сортувати іграшки", sortToys.name());
    }

    @Test
    public void testGetDescription() {
        String expectedDescription = "Функція дозволяє виконувати сортування іграшок. " +
                "Щоб виконати сортування іграшок необхідно ввести параметр сортування: назва, ціна або розмір. " +
                "Сортування виконується у порядку спадання. ";
        assertEquals(expectedDescription, sortToys.getDescription());
    }
}
