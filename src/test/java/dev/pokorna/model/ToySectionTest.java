package dev.pokorna.model;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ToySectionTest extends TestCase {

    private ToySection toySection;
    private Toy toy1, toy2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        toySection = new ToySection("Action Figures");
        toy1 = new Toy("Action Figure", "Figure", "Medium", 19.99);
        toy2 = new Toy("Robot", "Robot", "Large", 29.99);
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
    }

    public void testAddToy() {
        toySection.addToy(toy1);
        assertTrue(toySection.getToys().contains(toy1));

        toySection.addToy(toy2);
        assertTrue(toySection.getToys().contains(toy2));
    }

    public void testShowToys() {
        toySection.addToy(toy1);
        toySection.addToy(toy2);

        toySection.showToys();
        String expectedOutput = "Секція: Action Figures" + System.lineSeparator() +
                "Toy{name='Action Figure', type='Figure', size='Medium', price=19.99}" + System.lineSeparator() +
                "Toy{name='Robot', type='Robot', size='Large', price=29.99}" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testGetSectionName() {
        assertEquals("Action Figures", toySection.getSectionName());
    }

    public void testGetToys() {
        List<Toy> expectedToys = new ArrayList<>();
        toySection.setToys(expectedToys);
        assertEquals(expectedToys, toySection.getToys());
    }

    public void testSetSectionName() {
        toySection.setSectionName("Plush Toys");
        assertEquals("Plush Toys", toySection.getSectionName());
    }

    public void testSetToys() {
        List<Toy> newToys = new ArrayList<>();
        newToys.add(toy1);
        newToys.add(toy2);
        toySection.setToys(newToys);
        assertEquals(newToys, toySection.getToys());
    }
}
