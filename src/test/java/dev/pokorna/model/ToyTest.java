package dev.pokorna.model;

import junit.framework.TestCase;

public class ToyTest extends TestCase {

    private Toy toy;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        toy = new Toy("Action Figure", "Figure", "Medium", 19.99);
    }

    public void testTestToString() {
        String expected = "Toy{name='Action Figure', type='Figure', size='Medium', price=19.99}";
        assertEquals(expected, toy.toString());
    }

    public void testTestGetName() {
        assertEquals("Action Figure", toy.getName());
    }

    public void testGetType() {
        assertEquals("Figure", toy.getType());
    }

    public void testGetSize() {
        assertEquals("Medium", toy.getSize());
    }

    public void testGetPrice() {
        assertEquals(19.99, toy.getPrice(), 0.01);
    }

    public void testTestSetName() {
        toy.setName("Robot");
        assertEquals("Robot", toy.getName());
    }

    public void testSetType() {
        toy.setType("Robot");
        assertEquals("Robot", toy.getType());
    }

    public void testSetSize() {
        toy.setSize("Large");
        assertEquals("Large", toy.getSize());
    }

    public void testSetPrice() {
        toy.setPrice(29.99);
        assertEquals(29.99, toy.getPrice(), 0.01);
    }
}
