package dev.pokorna.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BudgetManagerTest {

    private BudgetManager budgetManager;

    @Before
    public void setUp() {
        budgetManager = new BudgetManager(1000.0);
    }

    @Test
    public void canAfford() {
        assertTrue(budgetManager.canAfford(500.0));
        assertFalse(budgetManager.canAfford(1500.0));
    }

    @Test
    public void spend() {
        budgetManager.spend(500.0);
        assertEquals(500.0, budgetManager.getBudget(), 0.01);
        budgetManager.spend(600.0);
        assertEquals(500.0, budgetManager.getBudget(), 0.01);
    }
}
