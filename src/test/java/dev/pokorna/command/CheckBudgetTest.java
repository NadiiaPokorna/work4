package dev.pokorna.command;

import dev.pokorna.model.BudgetManager;
import dev.pokorna.model.ToyRoom;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CheckBudgetTest extends TestCase {
    private ToyRoom toyRoom;
    private BudgetManager budgetManager;
    private CheckBudget checkBudget;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        budgetManager = new BudgetManager() {
            private double remainingBudget = 1000.0;
            @Override
            public double getRemainingBudget() {
                return remainingBudget;
            }
            public void setRemainingBudget(double budget) {
                this.remainingBudget = budget;
            }
        };
        toyRoom = new ToyRoom(budgetManager);
        checkBudget = new CheckBudget(toyRoom);
    }

    public void testExecute() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        checkBudget.execute();
        String expectedOutput = "Залишок бюджету: 1000.0 грн\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

    public void testName() {
        String expectedName = "Перевірити бюджет";
        assertEquals(expectedName, checkBudget.name());
    }

    public void testGetDescription() {
        String expectedDescription = "Функція дозволяє побачити поточний баланс.";
        assertEquals(expectedDescription, checkBudget.getDescription());
    }
}
