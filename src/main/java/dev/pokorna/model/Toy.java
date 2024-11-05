package dev.pokorna.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy {
    private String name;
    private String type;
    private String size;
    private double price;

    public Toy(String name, String type, String size, double price) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Toy {" + "name='" + name + '\'' + ", type='" + type + '\'' + ", size='" + size + '\'' + ", price=" + price + '}';
    }
}
