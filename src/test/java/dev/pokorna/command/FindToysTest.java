package dev.pokorna.command;

import dev.pokorna.model.Toy;
import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.ToySection;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class FindToysTest {
    private ToyRoom toyRoom;
    private FindToys findToys;

    @Before
    public void setUp() {
        toyRoom = new ToyRoom();
        findToys = new FindToys(toyRoom);

        ToySection toySection = new ToySection("Sect1");
        toyRoom.addToyToSection(new Toy("Test Toy 1", "Car", "Medium", 75.0), toySection);
        toyRoom.addToyToSection(new Toy("Test Toy 2", "Doll", "Small", 50.0), toySection);
        toyRoom.addToyToSection(new Toy("Test Toy 3", "Ball", "Large", 150.0), toySection);
        toyRoom.addToyToSection(new Toy("Test Toy 4", "Car", "Small", 45.0), toySection);
        toyRoom.addToyToSection(new Toy("Test Toy 5", "Car", "Medium", 85.0), toySection);
    }

    @Test
    public void testNoToysFound() {
        String simulatedInput = "200.0\n300.0\nLarge\nDoll\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);
        findToys.execute();
        List<Toy> matchingToys = toyRoom.findToysByCriteria(200.0, 300.0, "Large", "Doll");
        assertTrue(matchingToys.isEmpty());
    }
}
