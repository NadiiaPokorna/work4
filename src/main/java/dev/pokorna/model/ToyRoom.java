package dev.pokorna.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ToyRoom {
    private String id;
    private List<ToySection> sections;
    private BudgetManager budgetManager;

    public ToyRoom() {
        this.id = UUID.randomUUID().toString();
        this.sections = new ArrayList<>();
        this.budgetManager = new BudgetManager(20000);
    }

    public ToyRoom(BudgetManager budgetManager) {
        this.id = UUID.randomUUID().toString();
        this.sections = new ArrayList<>();
        this.budgetManager = budgetManager;
    }

    public void addSection(ToySection section) {
        sections.add(section);
    }

    public void addToyToSection(Toy toy, ToySection section) {
        if (budgetManager.canAfford(toy.getPrice())) {
            section.addToy(toy);
            budgetManager.spend(toy.getPrice());
        } else {
            System.out.println("Помилка: неможливо придбати іграшку. Недостатньо коштів.");
        }
    }

    public List<Toy> findToysByCriteria(double minPrice, double maxPrice, String size, String type) {
        List<Toy> result = new ArrayList<>();

        for (ToySection section : sections) {
            for (Toy toy : section.getToys()) {
                boolean matchesPrice = toy.getPrice() >= minPrice && toy.getPrice() <= maxPrice;
                boolean matchesSize = size.isEmpty() || toy.getSize().equalsIgnoreCase(size);
                boolean matchesType = type.isEmpty() || toy.getType().equalsIgnoreCase(type);

                if (matchesPrice && matchesSize && matchesType) {
                    result.add(toy);
                }
            }
        }

        return result;
    }
}
