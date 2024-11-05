package dev.pokorna.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetManager {
    private double budget;
    private double remainingBudget;

    public BudgetManager(double budget) {
        this.budget = budget;
    }

    public boolean canAfford(double price) {
        return budget >= price;
    }

    public void spend(double amount) {
        if (amount <= budget) {
            budget -= amount;
        } else {
            System.out.println("Помилка: недостатньо коштів.");
        }
    }
}
