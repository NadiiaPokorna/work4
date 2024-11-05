package dev.pokorna.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ToySection {
    private String sectionName;
    private List<Toy> toys;

    public ToySection(String sectionName) {
        this.sectionName = sectionName;
        this.toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void showToys() {
        System.out.println("Секція: " + sectionName);
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }
}

