package dev.pokorna.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ToyRoomTest extends TestCase {

    private ToyRoom toyRoom;
    private ToySection toySection;
    private Toy toy1, toy2;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        toyRoom = new ToyRoom();
        toySection = new ToySection("Action Figures");
        toyRoom.addSection(toySection);
        toy1 = new Toy("Action Figure", "Figure", "Medium", 19.99);
        toy2 = new Toy("Robot", "Robot", "Large", 29.99);
    }

    public void testAddSection() {
        ToySection newSection = new ToySection("Plush Toys");
        toyRoom.addSection(newSection);
        assertTrue(toyRoom.getSections().contains(newSection));
    }

    public void testAddToyToSection() {
        toyRoom.addToyToSection(toy1, toySection);
        assertTrue(toySection.getToys().contains(toy1));
        assertEquals(19980.01, toyRoom.getBudgetManager().getBudget(), 0.01);

        toyRoom.addToyToSection(toy2, toySection);
        assertTrue(toySection.getToys().contains(toy2));
        assertEquals(19950.02, toyRoom.getBudgetManager().getBudget(), 0.01);
    }

    public void testFindToysByCriteria() {
        toyRoom.addToyToSection(toy1, toySection);
        toyRoom.addToyToSection(toy2, toySection);

        List<Toy> foundToys = toyRoom.findToysByCriteria(10.0, 30.0, "Medium", "Figure");
        assertEquals(1, foundToys.size());
        assertEquals(toy1, foundToys.get(0));

        foundToys = toyRoom.findToysByCriteria(10.0, 40.0, "Large", "Robot");
        assertEquals(1, foundToys.size());
        assertEquals(toy2, foundToys.get(0));
    }

    public void testGetId() {
        assertNotNull(toyRoom.getId());
    }

    public void testGetSections() {
        assertNotNull(toyRoom.getSections());
        assertTrue(toyRoom.getSections().contains(toySection));
    }

    public void testGetBudgetManager() {
        assertNotNull(toyRoom.getBudgetManager());
        assertEquals(20000.0, toyRoom.getBudgetManager().getBudget(), 0.01);
    }

    public void testSetId() {
        String newId = "new-id";
        toyRoom.setId(newId);
        assertEquals(newId, toyRoom.getId());
    }

    public void testSetSections() {
        List<ToySection> newSections = new ArrayList<>();
        ToySection newSection = new ToySection("Plush Toys");
        newSections.add(newSection);
        toyRoom.setSections(newSections);
        assertEquals(newSections, toyRoom.getSections());
    }

    public void testSetBudgetManager() {
        BudgetManager newBudgetManager = new BudgetManager(5000);
        toyRoom.setBudgetManager(newBudgetManager);
        assertEquals(newBudgetManager, toyRoom.getBudgetManager());
        assertEquals(5000.0, toyRoom.getBudgetManager().getBudget(), 0.01);
    }
}
