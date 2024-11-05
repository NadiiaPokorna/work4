package dev.pokorna.command;

import dev.pokorna.model.Toy;
import dev.pokorna.model.ToyRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class FindToys implements Command {

    private static final Logger logger = LoggerFactory.getLogger(FindToys.class);

    private final ToyRoom toyRoom;

    public FindToys(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть мінімальну ціну для пошуку: ");
        double minPrice = scanner.nextDouble();

        System.out.println("Введіть максимальну ціну для пошуку: ");
        double maxPrice = scanner.nextDouble();

        scanner.nextLine();

        System.out.println("Введіть розмір іграшки для пошуку (маленький, середній, великий або залиште порожнім): ");
        String size = scanner.nextLine().trim();

        System.out.println("Введіть тип іграшки для пошуку (машинка, лялька, м'яч тощо або залиште порожнім): ");
        String type = scanner.nextLine().trim();

        List<Toy> matchingToys = toyRoom.findToysByCriteria(minPrice, maxPrice, size, type);

        logger.info("[Пошук іграшок за критеріями] Критерії (мін: {}, макс: {}, розмір: {}, тип: {}) -->> {}", minPrice, maxPrice, size, type, matchingToys);

        if (matchingToys.isEmpty()) {
            System.out.println("Іграшок за вказаними критеріями не знайдено.");
            logger.info("[Пошук іграшок за критеріями] Критерії (мін: {}, макс: {}, розмір: {}, тип: {}) -->> NONE", minPrice, maxPrice, size, type);
        } else {
            System.out.println("Знайдені іграшки:");
            for (Toy toy : matchingToys) {
                System.out.println(toy);
            }
        }
    }

    @Override
    public String name() {
        return "Знайти іграшки";
    }

    @Override
    public String getDescription() {
        return "Функція дозволяє знайти іграшку згідно із параметрами." +
                "Необхідні пареметри: мінімальна і максимальна ціна пошуку, розмір, тип.";
    }
}
