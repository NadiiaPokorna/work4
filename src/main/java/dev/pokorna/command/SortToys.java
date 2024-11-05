package dev.pokorna.command;

import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.Toy;
import dev.pokorna.model.ToySection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Scanner;

public class SortToys implements Command {

    private static final Logger logger = LoggerFactory.getLogger(SortToys.class);

    private final ToyRoom toyRoom;

    public SortToys(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Як ви хочете посортувати іграшки? (name/price/size): ");
        String sortBy = scanner.nextLine();

        for (ToySection section : toyRoom.getSections()) {
            switch (sortBy.toLowerCase()) {
                case "name":
                    section.getToys().sort(Comparator.comparing(Toy::getName));
                    break;
                case "price":
                    section.getToys().sort(Comparator.comparingDouble(Toy::getPrice));
                    break;
                case "size":
                    section.getToys().sort(Comparator.comparing(Toy::getSize));
                    break;
                default:
                    System.out.println("Невідомий параметр сортування: " + sortBy);
                    return;
            }
            System.out.println("Посортовані іграшки в секції: " + section.getSectionName() + " за параметром: " + sortBy);
            section.showToys();
            logger.info("[Сортування іграшок] Параметр сортування {} -->> {}", sortBy, section.getToys());
        }
    }

    @Override
    public String name() {
        return "Сортувати іграшки";
    }

    @Override
    public String getDescription() {
        return "Функція дозволяє виконувати сортування іграшок. " +
                "Щоб виконати сортування іграшок необхідно ввести параметр сортування: назва, ціна або розмір. " +
                "Сортування виконується у порядку спадання. ";
    }
}

